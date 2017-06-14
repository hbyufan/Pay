package http.filter;

import action.PCErrorPack;
import action.UserAction;
import http.HOpCodePayCenter;
import http.HSession;
import http.exception.HttpErrorException;
import protobuf.http.PCErrorProto.PCError;
import protobuf.http.PCErrorProto.PCErrorCode;
import protobuf.http.UserGroupProto.UserData;

public class TokenHttpFilter implements IHttpFilter {

	@Override
	public boolean httpFilter(HSession hSession) throws HttpErrorException {
		if (hSession.headParam.token == null) {
			if (HOpCodePayCenter.LOGIN == hSession.headParam.hOpCode || HOpCodePayCenter.VERIFY_NOTIFY == hSession.headParam.hOpCode || HOpCodePayCenter.CREATE_ORDER_RECORD == hSession.headParam.hOpCode) {
				// 可以通过
				return true;
			} else {
				PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_4, hSession.headParam.hOpCode);
				throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
			}
		}
		UserData userData = UserAction.getUser(hSession.headParam.token);
		if (userData == null) {
			PCError errorPack = PCErrorPack.create(PCErrorCode.ERROR_CODE_4, hSession.headParam.hOpCode);
			throw new HttpErrorException(HOpCodePayCenter.PC_ERROR, errorPack);
		}
		hSession.otherData = userData;
		return true;
	}

}
