package com.shihang.myframe.inter;


import org.xutils.common.Callback.Cancelable;

public abstract class HttpCallBack {

    private Cancelable http = null;

    public void setHttp(Cancelable http){
        this.http = http;
    }

    public Cancelable getHttp(){
        return http;
    }

    public int getHttpCode(){
        return http == null ?  -1 : http.hashCode() ;
    }

    public void start(){};

    public abstract void success(boolean success, String result);


/* 成功
12-10 11:41:13.846 16153-16153/com.hunuo.svnxutildemo I/MainActivity: onSuccess():{"session_id":"kat1449718921"}
12-10 11:41:13.855 16153-16153/com.hunuo.svnxutildemo I/MainActivity: onFinished()
*/


/* 没网
12-10 11:41:35.493 16153-16153/com.hunuo.svnxutildemo I/MainActivity: onError():
                                                                      ex:java.net.UnknownHostException: Unable to resolve host "xianmw.gz10.hunuo.net": No address associated with hostname
                                                                      isOnCallback:false
12-10 11:41:35.493 16153-16153/com.hunuo.svnxutildemo I/MainActivity: onFinished()
*/


/* 网络连接超时
12-10 11:49:00.312 16153-16153/com.hunuo.svnxutildemo I/MainActivity: onError():
                                                                      ex:java.net.SocketTimeoutException
                                                                      isOnCallback:false
12-10 11:49:00.315 16153-16153/com.hunuo.svnxutildemo I/MainActivity: onFinished()
*/




/* 取消
12-10 11:43:54.644 16153-16153/com.hunuo.svnxutildemo I/MainActivity: onCancelled():
                                                                      cex:org.xutils.common.Callback$CancelledException:
12-10 11:43:54.646 16153-16153/com.hunuo.svnxutildemo I/MainActivity: onFinished()
*/


}