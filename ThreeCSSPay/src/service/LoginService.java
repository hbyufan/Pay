package service;

import java.util.HashMap;
import java.util.Map;

import action.PCErrorPack;
import action.UserAction;
import http.HOpCodePayCenter;
import http.HSession;
import http.HttpPacket;
import http.IHttpListener;
import http.exception.HttpErrorException;
import protobuf.http.LoginProto.LoginC;
import protobuf.http.LoginProto.LoginS;
import protobuf.http.PCErrorProto.PCError;
import protobuf.http.PCErrorProto.PCErrorCode;
import protobuf.http.UserGroupProto.UserData;

public class LoginService implements IHttpListener {

	@Override
	public Map<Integer, String> getHttps() throws Exception {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(HOpCodePayCenter.LOGIN, "loginHandle");
		return map;
	}

	@Override
	public Object getInstance() {
		return this;
	}

	public HttpPacket loginHandle(HSession hSession) throws HttpErrorException {
		LoginC message = (LoginC) hSession.httpPacket.getData();
		UserData userData = UserAction.getUser(message.getToken());
		if (userData == null) {
			PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_4, hSession.headParam.hOpCode);
			throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
		}
		LoginS.Builder builder = LoginS.newBuilder();
		builder.setHOpCode(hSession.headParam.hOpCode);
		HttpPacket packet = new HttpPacket(hSession.headParam.hOpCode, builder.build());
		return packet;
	}
}
