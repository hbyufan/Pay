package config;

import config.ConfigManager.JsonConfigType;
import log.LogManager;
import net.sf.json.JSONObject;

public class CommonConfigPayCenter extends CommonConfig {

	public static String ALIPAY_PARTNER;
	public static String ALIPAY_SELLER_ID;
	public static String ALIPAY_KEY;
	public static String ALIPAY_NOTIFY_URL;
	public static String ALIPAY_RETURN_URL;

	public static String UCENTER_URL;

	public static String PAY_SUCCESS_URL;
	public static String PAY_FAIL_URL;

	public static int NOTIFY_EXPIRE_TIME;
	
	public static int MAX_NOTIFY_TIME;
	
	public static int NOTIFY_INTERVAL;

	public static void init() {
		LogManager.initLog.info("初始化CommonConfigPayCenter");
		JSONObject configExt = ConfigManager.getJsonData(JsonConfigType.CONFIGEXT.getTypeValue());
		ALIPAY_PARTNER = configExt.getJSONArray("alipay_partner").getString(0);
		ALIPAY_SELLER_ID = configExt.getJSONArray("alipay_seller_id").getString(0);
		ALIPAY_KEY = configExt.getJSONArray("alipay_key").getString(0);
		ALIPAY_NOTIFY_URL = configExt.getJSONArray("alipay_notify_url").getString(0);
		ALIPAY_RETURN_URL = configExt.getJSONArray("alipay_return_url").getString(0);

		UCENTER_URL = configExt.getJSONArray("ucenter_url").getString(0);
		PAY_SUCCESS_URL = configExt.getJSONArray("pay_success_url").getString(0);
		PAY_FAIL_URL = configExt.getJSONArray("pay_fail_url").getString(0);

		NOTIFY_EXPIRE_TIME = configExt.getJSONArray("notify_expire_time").getInt(0);
		
		MAX_NOTIFY_TIME = configExt.getJSONArray("max_notify_time").getInt(0);
		
		NOTIFY_INTERVAL = configExt.getJSONArray("notify_interval").getInt(0);
		LogManager.initLog.info("初始化CommonConfigPayCenter完成");
	}
}
