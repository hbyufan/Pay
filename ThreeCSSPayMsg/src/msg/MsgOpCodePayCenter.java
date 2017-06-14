package msg;

import protobuf.msg.AddNotifyOuterClass.AddNotify;

public class MsgOpCodePayCenter extends MsgOpCode {
	public static String ADD_NOTIFY = "ADD_NOTIFY";

	public static void init() {
		msgOpCodeMap.put(ADD_NOTIFY, AddNotify.class);
	}
}
