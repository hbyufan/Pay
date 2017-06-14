package service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;

import action.NotifyAction;
import action.OrderRecordAction;
import action.PCErrorPack;
import config.CommonConfigPayCenter;
import config.NotifyConfig;
import config.OrderRecordConfig;
import dao.model.base.Notify;
import dao.model.base.OrderGoods;
import dao.model.ext.OrderRecordExt;
import http.HOpCodePayCenter;
import http.HSession;
import http.HttpPacket;
import http.IHttpListener;
import http.exception.HttpErrorException;
import protobuf.http.PCErrorProto.PCError;
import protobuf.http.PCErrorProto.PCErrorCode;
import protobuf.http.PayProto.GetPayHTMLC;
import protobuf.http.PayProto.GetPayHTMLS;
import protobuf.http.PayProto.GetReturnUrlC;
import protobuf.http.PayProto.GetReturnUrlS;
import protobuf.http.PayProto.VerifyNotifyC;
import protobuf.http.PayProto.VerifyNotifyS;
import protobuf.http.UserGroupProto.UserData;

public class PayService implements IHttpListener, IService {

	@Override
	public Map<Integer, String> getHttps() throws Exception {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(HOpCodePayCenter.GET_PAY_HTML, "getPayHTMLHandle");
		map.put(HOpCodePayCenter.GET_RETURN_URL, "getReturnUrlHandle");
		map.put(HOpCodePayCenter.VERIFY_NOTIFY, "verifyNotifyHandle");
		return map;
	}

	@Override
	public Object getInstance() {
		return this;
	}

	public HttpPacket verifyNotifyHandle(HSession hSession) throws HttpErrorException {
		VerifyNotifyC message = (VerifyNotifyC) hSession.httpPacket.getData();
		Notify notify = NotifyAction.getNotify(message.getNotifyId());
		if (notify != null && notify.getAppId().equals(message.getAppId())) {
			Date date = new Date();
			if (date.getTime() < notify.getNotifyExpireTime().getTime()) {
				VerifyNotifyS.Builder builder = VerifyNotifyS.newBuilder();
				builder.setHOpCode(hSession.headParam.hOpCode);
				builder.setResult(1);
				HttpPacket packet = new HttpPacket(hSession.headParam.hOpCode, builder.build());
				return packet;
			}
		}
		VerifyNotifyS.Builder builder = VerifyNotifyS.newBuilder();
		builder.setHOpCode(hSession.headParam.hOpCode);
		builder.setResult(2);
		HttpPacket packet = new HttpPacket(hSession.headParam.hOpCode, builder.build());
		return packet;
	}

	public HttpPacket getReturnUrlHandle(HSession hSession) throws HttpErrorException {
		GetReturnUrlC message = (GetReturnUrlC) hSession.httpPacket.getData();
		OrderRecordExt orderRecord = OrderRecordAction.getOrderRecordById(message.getOrderRecordId());
		if (orderRecord == null) {
			PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_1, hSession.headParam.hOpCode);
			throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
		}
		// 该订单必须完成或者失败
		if (orderRecord.getOrderRecordPayStatus().intValue() == OrderRecordConfig.PAY_STATUS_UNPAID) {
			PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_5, hSession.headParam.hOpCode);
			throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
		}
		// 该订单是否是此用户的
		UserData userData = (UserData) hSession.otherData;
		hSession.runMonitor.putMonitor("userId:" + userData.getUserId());
		hSession.runMonitor.putMonitor("orderRecord.getOrderRecordUserId():" + orderRecord.getOrderRecordUserId());
		if (!userData.getUserId().equals(orderRecord.getOrderRecordUserId())) {
			PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_2, hSession.headParam.hOpCode);
			throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
		}
		Notify notify = NotifyAction.createNotify(orderRecord.getAppId(), orderRecord.getOrderRecordId(), NotifyConfig.TYPE_RETURN);
		if (notify == null) {
			PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_6, hSession.headParam.hOpCode);
			throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
		}
		String returnUrl = OrderRecordAction.getReturnUrl(orderRecord, notify);
		if (returnUrl == null) {
			PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_7, hSession.headParam.hOpCode);
			throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
		}

		GetReturnUrlS.Builder builder = GetReturnUrlS.newBuilder();
		builder.setHOpCode(hSession.headParam.hOpCode);
		builder.setReturnUrl(returnUrl);
		HttpPacket packet = new HttpPacket(hSession.headParam.hOpCode, builder.build());
		return packet;
	}

	public HttpPacket getPayHTMLHandle(HSession hSession) throws HttpErrorException {
		GetPayHTMLC message = (GetPayHTMLC) hSession.httpPacket.getData();
		OrderRecordExt orderRecord = OrderRecordAction.getOrderRecordById(message.getOrderRecordId());
		if (orderRecord == null) {
			PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_1, hSession.headParam.hOpCode);
			throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
		}
		List<OrderGoods> orderGoodsList = OrderRecordAction.getOrderGoodsListByOrderRecordId(orderRecord.getOrderRecordId());
		if (orderGoodsList == null) {
			PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_3, hSession.headParam.hOpCode);
			throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
		}
		orderRecord.orderGoodsArray = orderGoodsList;
		// 该订单是否是此用户的
		UserData userData = (UserData) hSession.otherData;
		if (!userData.getUserId().equals(orderRecord.getOrderRecordUserId())) {
			PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_2, hSession.headParam.hOpCode);
			throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
		}
		String subject = OrderRecordAction.getSubject(orderRecord);
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_id", AlipayConfig.seller_id);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		sParaTemp.put("out_trade_no", orderRecord.getOrderRecordId());
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", orderRecord.getOrderRecordTotalPrice().toString());
		sParaTemp.put("body", "none");
		// 其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
		// 如sParaTemp.put("参数名","参数值");

		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "submit");
		GetPayHTMLS.Builder builder = GetPayHTMLS.newBuilder();
		builder.setHOpCode(hSession.headParam.hOpCode);
		builder.setPayHtml(sHtmlText);
		HttpPacket packet = new HttpPacket(hSession.headParam.hOpCode, builder.build());
		return packet;
	}

	@Override
	public void init() throws Exception {
		AlipayConfig.partner = CommonConfigPayCenter.ALIPAY_PARTNER;
		AlipayConfig.seller_id = CommonConfigPayCenter.ALIPAY_SELLER_ID;
		AlipayConfig.notify_url = CommonConfigPayCenter.ALIPAY_NOTIFY_URL;
		AlipayConfig.return_url = CommonConfigPayCenter.ALIPAY_RETURN_URL;
		AlipayConfig.key = CommonConfigPayCenter.ALIPAY_KEY;
	}

}
