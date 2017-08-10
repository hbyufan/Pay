function RecordMediator() {
    this.token;
    this.userId;
    this.init = function (view) {
        this.token = $T.getUrlParam.getUrlParam($T.httpConfig.TOKEN);
        this.userId = $T.getUrlParam.getUrlParam('userId');
        if (this.token == null || this.userId == null) {
            window.location.href = 'login.html';
        }
        $T.cookieParam.setCookieParam($T.cookieName.TOKEN, this.token);
        $("#orderRecordUserId").val(this.userId);
        $("#orderRecordOrderId").val(Math.uuid());
        $("#orderRecordReturnUrl").val($T.url.clientUrl + 'success.html');
        $("#orderRecordNotifyUrl").val($T.url.clientUrl + 'success.html');
        $T.appProxy.getAppList(null, null, null, 1)
    }
    // 注销方法
    this.dispose = function () {

    }
    // 关心消息数组
    this.listNotificationInterests = [$T.notificationExt.GET_APP_LIST_SUCCESS];
    // 关心的消息处理
    this.handleNotification = function (data) {
        switch (data[0].name) {
            case $T.notificationExt.GET_APP_LIST_SUCCESS:
                $("#appId").val(data[0].body['appList'][0].appId)
                break;
        }
    }
    this.advanceTime = function (passedTime) {

    }
}
$T.recordMediator = new RecordMediator();