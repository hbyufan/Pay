package server;

import java.util.TimeZone;

import config.CommonConfigPayCenter;
import http.HOpCodePayCenter;
import http.HOpCodeUCenter;
import init.IExpand;
import init.Init;
import service.AppService;
import service.LoginService;
import service.OrderRecordService;
import service.PayService;

public class Expand implements IExpand {

	@Override
	public void init() throws Exception {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		CommonConfigPayCenter.init();
		HOpCodePayCenter.init();
		HOpCodeUCenter.init();
		Init.registerService(AppService.class);
		Init.registerService(OrderRecordService.class);
		Init.registerService(PayService.class);
		Init.registerService(LoginService.class);
	}

	@Override
	public void threadInit() throws Exception {

	}

}
