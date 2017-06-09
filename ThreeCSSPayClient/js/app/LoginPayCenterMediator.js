function LoginPayCenterMediator() {
    this.orderRecordId;
    this.token;
    this.init = function (view) {
        this.token = $T.getUrlParam.getUrlParam($T.httpConfig.TOKEN);
        if (this.token == null) {
            // token是空应该提示无法登陆
            alert("token为空");
            return;
        }
        this.orderRecordId = $T.getUrlParam.getUrlParam("orderRecordId");
        if (this.orderRecordId == null) {
            alert("订单记录为空");
            return;
        }
        // 设置cookie
        $T.loginProxy.login(this.token);
    }
    // 注销方法
    this.dispose = function () {

    }
    // 关心消息数组
    this.listNotificationInterests = [$T.notificationExt.LOGIN_SUCCESS];
    // 关心的消息处理
    this.handleNotification = function (data) {
        switch (data[0].name) {
            case $T.notificationExt.LOGIN_SUCCESS:
                $T.cookieParam.setCookieParam($T.cookieName.TOKEN, this.token);
                window.location.href = "index.html?orderRecordId=" + this.orderRecordId;
                break;
        }
    }
    this.advanceTime = function (passedTime) {

    }
}
$T.loginPayCenterMediator = new LoginPayCenterMediator();