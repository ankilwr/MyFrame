package com.shihang.myframe.inter;


import org.xutils.common.Callback.Cancelable;

public abstract class HttpLinkCallBack {

    private Cancelable http = null;

    public Cancelable getHttp(){
        return http;
    }

    public int getHttpCode(){
        return http == null ?  -1 : http.hashCode() ;
    }

    public void start(){};

    public void loading(String requestTag, Cancelable http){
        this.http = http;
    };

    public abstract boolean httpResult(boolean success, String requestTAG, String result);

    public void finish(String result){};

}