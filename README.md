# threecss-pay

threecss-pay是基于ThreeCSS分布式框架开发的一款支付平台。

该项目由ThreeCSSPay(服务器)与ThreeCSSPayClient(客户端)两个子项目组成。


体验地址：
https://pay.threecss.com


依赖身份系统：
threecss-identity


打版本：在项目根目录下，配置好build-custom.properties每个项目的路径及发布路径，执行

	ant


配置：

	dist/ThreeCSSPayClient/js/app/Url.js-----访问支付服务器与身份系统服务器

	dist/ThreeCSSPayConfigData/configext.json----访问身份系统及其他配置

	dist/ThreeCSSPayConfigData/mybatis-config.xml---访问支付数据库

	dist/ThreeCSSPay.properties----ThreeCSSPayConfigData在服务器路径


推荐环境：

	jdk-8u121

	apache-tomcat-8.5.12

	MariaDB-10.1.22

	CentOS-7-1611


发布项目：

1、该项目依赖threecss-identity，需要先部署身份系统，具体详见：

https://github.com/dianbaer/threecss-identity

2、安装数据库
	
	create database threecsspay
	
	source ****/threecsspay.sql

3、将ThreeCSSPayConfigData放入服务器某个路径，例如
	
	/home/ThreeCSSPayConfigData

4、将ThreeCSSPay.properties放入tomcat根目录下，例如
	
	/home/tomcat/ThreeCSSPay.properties
	
	并修改config_dir对应的ThreeCSSPayConfigData路径

5、将ThreeCSSPayClient与ThreeCSSPay.war放入tomcat/webapps，例如
	
	/home/tomcat/webapps/ThreeCSSPay.war
	
	/home/tomcat/webapps/ThreeCSSPayClient


threecss-pay功能：

1、创建支付应用（通过支付应用id号，进行支付）：
	
	创建、修改、获取支付应用，获取支付应用列表。
	
2、创建订单（通过支付应用id与该应用自己产生的订单号，创建订单）：

	创建、修改、获取订单，获取订单列表。
	
3、通过token与支付中心产生的订单号进行支付（基于threecss-identity的token，如果对接其他身份系统需要二次开发）
	
	例如：https://pay.threecss.com/loginPayCenter.html?token=248778127e2f4937b570825a5f80b61a&orderRecordId=2d67e747337240e98dbc202dbe92538e
	
4、支付环节（目前只支持支付宝）

	与支付宝进行的支付环节，支持RSA或MD5
	
5、支付成功回调及通知（基于ThreeCSS服务器统一线程模型，高效且线程安全）

	创建订单时，填写回调地址与通知地址，支付成功后会回调相应URL并通知相应URL
	例如：https://pay.threecss.com/success.html?orderRecordId=3440682343a646fc804cc79014375345&orderRecordOrderId=F264DA7A-1DED-474B-A559-51826316D02E&orderRecordPayStatus=2&notifyId=44bc0a7ff7b1428991b2df7420c59779

