package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.AppAction;
import action.OrderRecordAction;
import action.PCErrorPack;
import dao.model.base.App;
import dao.model.base.OrderGoods;
import dao.model.ext.OrderRecordExt;
import http.HOpCodePayCenter;
import http.HSession;
import http.HttpPacket;
import http.IHttpListener;
import http.exception.HttpErrorException;
import protobuf.http.OrderRecordProto.CreateOrderRecordC;
import protobuf.http.OrderRecordProto.CreateOrderRecordS;
import protobuf.http.OrderRecordProto.GetOrderRecordC;
import protobuf.http.OrderRecordProto.GetOrderRecordListC;
import protobuf.http.OrderRecordProto.GetOrderRecordListS;
import protobuf.http.OrderRecordProto.GetOrderRecordS;
import protobuf.http.OrderRecordProto.UpdateOrderRecordC;
import protobuf.http.OrderRecordProto.UpdateOrderRecordS;
import protobuf.http.PCErrorProto.PCError;
import protobuf.http.PCErrorProto.PCErrorCode;
import protobuf.http.UserGroupProto.UserData;
import tool.PageFormat;
import tool.PageObj;

public class OrderRecordService implements IHttpListener {

	@Override
	public Map<Integer, String> getHttps() throws Exception {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(HOpCodePayCenter.CREATE_ORDER_RECORD, "createOrderRecordHandle");
		//map.put(HOpCodePayCenter.UPDATE_ORDER_RECORD, "updateOrderRecordHandle");
		map.put(HOpCodePayCenter.GET_ORDER_RECORD, "getOrderRecordHandle");
		map.put(HOpCodePayCenter.GET_ORDER_RECORD_LIST, "getOrderRecordListHandle");
		return map;
	}

	@Override
	public Object getInstance() {
		return this;
	}

	public HttpPacket createOrderRecordHandle(HSession hSession) {
		CreateOrderRecordC message = (CreateOrderRecordC) hSession.httpPacket.getData();
		String appId = message.getAppId();
		App app = AppAction.getAppById(appId);
		if (app == null) {
			return null;
		}
		OrderRecordExt orderRecord = OrderRecordAction.createOrderRecord(appId, message.getOrderRecordOrderId(), message.getOrderRecordTotalPrice(), message.getOrderRecordUserId(), message.getOrderRecordOrderDetail(), message.getOrderRecordUserName(), message.getOrderRecordReturnUrl(), message.getOrderRecordNotifyUrl(), message.getOrderGoodsArrayList());
		if (orderRecord == null) {
			return null;
		}
		CreateOrderRecordS.Builder builder = CreateOrderRecordS.newBuilder();
		builder.setHOpCode(hSession.headParam.hOpCode);
		builder.setOrderRecordData(OrderRecordAction.getOrderRecordBuilder(orderRecord));
		HttpPacket packet = new HttpPacket(hSession.headParam.hOpCode, builder.build());
		return packet;
	}

	public HttpPacket updateOrderRecordHandle(HSession hSession) {
		UpdateOrderRecordC message = (UpdateOrderRecordC) hSession.httpPacket.getData();
		OrderRecordExt orderRecord = OrderRecordAction.updateOrderRecord(message.getOrderRecordId(), message.getOrderRecordPayStatus(), message.getOrderRecordStatus(), message.getOrderRecordPayChannel(), message.getOrderRecordNotifyResult(), message.getOrderRecordNotifyTime());
		if (orderRecord == null) {
			return null;
		}
		List<OrderGoods> orderGoodsList = OrderRecordAction.getOrderGoodsListByOrderRecordId(orderRecord.getOrderRecordId());
		if (orderGoodsList == null) {
			return null;
		}
		orderRecord.orderGoodsArray = orderGoodsList;
		UpdateOrderRecordS.Builder builder = UpdateOrderRecordS.newBuilder();
		builder.setHOpCode(hSession.headParam.hOpCode);
		builder.setOrderRecordData(OrderRecordAction.getOrderRecordBuilder(orderRecord));
		HttpPacket packet = new HttpPacket(hSession.headParam.hOpCode, builder.build());
		return packet;
	}

	public HttpPacket getOrderRecordHandle(HSession hSession) throws HttpErrorException {
		GetOrderRecordC message = (GetOrderRecordC) hSession.httpPacket.getData();
		OrderRecordExt orderRecord = OrderRecordAction.getOrderRecordById(message.getOrderRecordId());
		if (orderRecord == null) {
			PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_1, hSession.headParam.hOpCode);
			throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
		}
		// 该订单是否是此用户的
		UserData userData = (UserData) hSession.otherData;
		if (!userData.getUserId().equals(orderRecord.getOrderRecordUserId())) {
			PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_2, hSession.headParam.hOpCode);
			throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
		}
		List<OrderGoods> orderGoodsList = OrderRecordAction.getOrderGoodsListByOrderRecordId(orderRecord.getOrderRecordId());
		if (orderGoodsList == null) {
			PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_3, hSession.headParam.hOpCode);
			throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
		}
		orderRecord.orderGoodsArray = orderGoodsList;
		GetOrderRecordS.Builder builder = GetOrderRecordS.newBuilder();
		builder.setHOpCode(hSession.headParam.hOpCode);
		builder.setOrderRecordData(OrderRecordAction.getOrderRecordBuilder(orderRecord));
		HttpPacket packet = new HttpPacket(hSession.headParam.hOpCode, builder.build());
		return packet;
	}

	public HttpPacket getOrderRecordListHandle(HSession hSession) {
		GetOrderRecordListC message = (GetOrderRecordListC) hSession.httpPacket.getData();
		long count = OrderRecordAction.getOrderRecordListCount(message.getAppId(), message.getOrderRecordOrderId(), message.getOrderRecordCreateTimeGreaterThan(), message.getOrderRecordCreateTimeLessThan(), message.getOrderRecordPayStatus(), message.getOrderRecordStatus(), message.getOrderRecordUserId(), message.getOrderRecordPayChannel(), message.getOrderRecordOrderDetail(), message.getOrderRecordNotifyResult());
		if (count == -1) {
			return null;
		}
		PageObj pageObj = PageFormat.getStartAndEnd(message.getCurrentPage(), message.getPageSize(), (int) count);
		List<OrderRecordExt> orderRecordList = OrderRecordAction.getOrderRecordList(message.getAppId(), message.getOrderRecordOrderId(), message.getOrderRecordCreateTimeGreaterThan(), message.getOrderRecordCreateTimeLessThan(), message.getOrderRecordPayStatus(), message.getOrderRecordStatus(), message.getOrderRecordUserId(), message.getOrderRecordPayChannel(), message.getOrderRecordOrderDetail(), message.getOrderRecordNotifyResult(), pageObj.start, pageObj.pageSize);
		if (orderRecordList == null) {
			return null;
		}
		GetOrderRecordListS.Builder builder = GetOrderRecordListS.newBuilder();
		builder.setHOpCode(hSession.headParam.hOpCode);
		for (int i = 0; i < orderRecordList.size(); i++) {
			OrderRecordExt orderRecord = orderRecordList.get(i);
			List<OrderGoods> orderGoodsList = OrderRecordAction.getOrderGoodsListByOrderRecordId(orderRecord.getOrderRecordId());
			orderRecord.orderGoodsArray = orderGoodsList;
			builder.addOrderRecordList(OrderRecordAction.getOrderRecordBuilder(orderRecord));
		}
		builder.setCurrentPage(pageObj.currentPage);
		builder.setAllNum(pageObj.allNum);
		builder.setPageSize(pageObj.pageSize);
		builder.setTotalPage(pageObj.totalPage);
		HttpPacket packet = new HttpPacket(hSession.headParam.hOpCode, builder.build());
		return packet;
	}

}
