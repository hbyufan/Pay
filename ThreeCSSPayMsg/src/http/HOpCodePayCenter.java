package http;

import protobuf.http.AppProto.CreateAppC;
import protobuf.http.AppProto.CreateAppS;
import protobuf.http.AppProto.GetAppC;
import protobuf.http.AppProto.GetAppListC;
import protobuf.http.AppProto.GetAppListS;
import protobuf.http.AppProto.GetAppS;
import protobuf.http.AppProto.UpdateAppC;
import protobuf.http.AppProto.UpdateAppS;
import protobuf.http.LoginProto.LoginC;
import protobuf.http.LoginProto.LoginS;
import protobuf.http.OrderRecordProto.CreateOrderRecordC;
import protobuf.http.OrderRecordProto.CreateOrderRecordS;
import protobuf.http.OrderRecordProto.GetOrderRecordC;
import protobuf.http.OrderRecordProto.GetOrderRecordListC;
import protobuf.http.OrderRecordProto.GetOrderRecordListS;
import protobuf.http.OrderRecordProto.GetOrderRecordS;
import protobuf.http.OrderRecordProto.UpdateOrderRecordC;
import protobuf.http.OrderRecordProto.UpdateOrderRecordS;
import protobuf.http.PCErrorProto.PCError;
import protobuf.http.PayProto.GetPayHTMLC;
import protobuf.http.PayProto.GetPayHTMLS;
import protobuf.http.PayProto.GetReturnUrlC;
import protobuf.http.PayProto.GetReturnUrlS;
import protobuf.http.PayProto.VerifyNotifyC;
import protobuf.http.PayProto.VerifyNotifyS;

public class HOpCodePayCenter extends HOpCode {

	public static int PC_ERROR = 199;

	public static int CREATE_APP = 200;
	public static int UPDATE_APP = 201;
	public static int GET_APP = 202;
	public static int GET_APP_LIST = 203;

	public static int CREATE_ORDER_RECORD = 210;
	public static int UPDATE_ORDER_RECORD = 211;
	public static int GET_ORDER_RECORD = 212;
	public static int GET_ORDER_RECORD_LIST = 213;

	public static int GET_PAY_HTML = 220;
	public static int GET_RETURN_URL = 221;
	public static int VERIFY_NOTIFY = 222;

	public static int LOGIN = 230;

	public static void init() {

		Class<?>[] sendAndReturn = new Class[2];
		sendAndReturn[0] = null;
		sendAndReturn[1] = PCError.class;
		hOpCodeMap.put(PC_ERROR, sendAndReturn);

		sendAndReturn = new Class[2];
		sendAndReturn[0] = CreateAppC.class;
		sendAndReturn[1] = CreateAppS.class;
		hOpCodeMap.put(CREATE_APP, sendAndReturn);

		sendAndReturn = new Class[2];
		sendAndReturn[0] = UpdateAppC.class;
		sendAndReturn[1] = UpdateAppS.class;
		hOpCodeMap.put(UPDATE_APP, sendAndReturn);

		sendAndReturn = new Class[2];
		sendAndReturn[0] = GetAppC.class;
		sendAndReturn[1] = GetAppS.class;
		hOpCodeMap.put(GET_APP, sendAndReturn);

		sendAndReturn = new Class[2];
		sendAndReturn[0] = GetAppListC.class;
		sendAndReturn[1] = GetAppListS.class;
		hOpCodeMap.put(GET_APP_LIST, sendAndReturn);

		sendAndReturn = new Class[2];
		sendAndReturn[0] = CreateOrderRecordC.class;
		sendAndReturn[1] = CreateOrderRecordS.class;
		hOpCodeMap.put(CREATE_ORDER_RECORD, sendAndReturn);

		sendAndReturn = new Class[2];
		sendAndReturn[0] = UpdateOrderRecordC.class;
		sendAndReturn[1] = UpdateOrderRecordS.class;
		hOpCodeMap.put(UPDATE_ORDER_RECORD, sendAndReturn);

		sendAndReturn = new Class[2];
		sendAndReturn[0] = GetOrderRecordC.class;
		sendAndReturn[1] = GetOrderRecordS.class;
		hOpCodeMap.put(GET_ORDER_RECORD, sendAndReturn);

		sendAndReturn = new Class[2];
		sendAndReturn[0] = GetOrderRecordListC.class;
		sendAndReturn[1] = GetOrderRecordListS.class;
		hOpCodeMap.put(GET_ORDER_RECORD_LIST, sendAndReturn);

		sendAndReturn = new Class[2];
		sendAndReturn[0] = GetPayHTMLC.class;
		sendAndReturn[1] = GetPayHTMLS.class;
		hOpCodeMap.put(GET_PAY_HTML, sendAndReturn);

		sendAndReturn = new Class[2];
		sendAndReturn[0] = GetReturnUrlC.class;
		sendAndReturn[1] = GetReturnUrlS.class;
		hOpCodeMap.put(GET_RETURN_URL, sendAndReturn);

		sendAndReturn = new Class[2];
		sendAndReturn[0] = VerifyNotifyC.class;
		sendAndReturn[1] = VerifyNotifyS.class;
		hOpCodeMap.put(VERIFY_NOTIFY, sendAndReturn);

		sendAndReturn = new Class[2];
		sendAndReturn[0] = LoginC.class;
		sendAndReturn[1] = LoginS.class;
		hOpCodeMap.put(LOGIN, sendAndReturn);

	}
}
