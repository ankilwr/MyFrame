package com.shihang.myframe.manager;

import android.util.Log;
import com.shihang.myframe.inter.HttpCallBack;
import com.shihang.myframe.inter.HttpLinkCallBack;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.KeyValue;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.util.List;

public class HttpManager {

    public Cancelable GET(final String TAG, String url, final HttpCallBack resultListener){
        //url = url.trim();
        RequestParams params = new RequestParams(url);
        httpLog(TAG, params);

        if(resultListener != null){
            resultListener.start();
        }

        Cancelable http = x.http().request(HttpMethod.GET, params, new CommonCallback<String>() {

            private boolean isSuccess = false;
            private String msg = null;

            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "requestResult:success()" + "\n" + "result:" + result);
                isSuccess = true;
                msg = result;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i(TAG, "requestResult:error()" + "\n" + "result:" + ex.toString());
                isSuccess = false;
                msg = ex.toString();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i(TAG, "requestResult:onCancelled()" + "\n" + "result:" + cex.toString());
                isSuccess = false;
                //msg = cex.toString();
            }

            @Override
            public void onFinished() {
                if(resultListener != null){
                    resultListener.success(isSuccess, msg);
                }
            }
        });
        if(resultListener != null){
            resultListener.setHttp(http);
        }
        return http;
    }

    public Cancelable POST(final String TAG, RequestParams params,final HttpCallBack resultListener){
        httpLog(TAG, params);
        if(resultListener != null){
            resultListener.start();
        }
        Cancelable http = x.http().request(HttpMethod.POST, params, new CommonCallback<String>() {
            private boolean isSuccess = false;
            private String msg = null;

            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "requestResult:success()" + "\n" + "result:" + result);
                isSuccess = true;
                msg = result;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i(TAG, "requestResult:error()" + "\n" + "result:" + ex.toString());
                isSuccess = false;
                msg = ex.toString();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i(TAG, "requestResult:onCancelled()" + "\n" + "result:" + cex.toString());
                isSuccess = false;
                //msg = cex.toString();
            }

            @Override
            public void onFinished() {
                if(resultListener != null){
                    resultListener.success(isSuccess, msg);
                }
            }
        });
        if(resultListener != null){
            resultListener.setHttp(http);
        }
        return http;
    }

    public void requestLink(String TAG, List<Request> requestParamList, HttpLinkCallBack httpLinkCallBack){
        if(httpLinkCallBack != null){
            httpLinkCallBack.start();
        }
        if(requestParamList == null || requestParamList.size() == 0){
            if(httpLinkCallBack != null){
                httpLinkCallBack.finish(null);
            }
            return;
        }
        requestLink(TAG, requestParamList, httpLinkCallBack, 0);
    }


    private void requestLink(final String TAG, final List<Request> requestParamList, final HttpLinkCallBack httpLinkCallBack, final int requestIndex){
        final Request request = requestParamList.get(requestIndex);
        HttpCallBack resultCallBack = new HttpCallBack() {
            @Override
            public void success(boolean success, String result) {
                if(httpLinkCallBack != null){
                    boolean next = httpLinkCallBack.httpResult(success, request.getTAG(), result);
                    int index = requestIndex + 1;
                    if(next && index < requestParamList.size()){
                        requestLink(TAG,requestParamList, httpLinkCallBack, index);
                    }else{
                        httpLinkCallBack.finish(result);
                    }
                }
            }
        };
        Cancelable http = null;
        if(HttpMethod.GET == request.getHttpMethod()){
            http = GET(TAG, request.getUrl(), resultCallBack);
        }else if(HttpMethod.POST == request.getHttpMethod()){
            http = POST(TAG, request.getParam(), resultCallBack);
        }
        if(httpLinkCallBack != null){
            httpLinkCallBack.loading(request.getTAG(), http);
        }
    }


    private void httpLog(String TAG, RequestParams params){
        StringBuilder strBuild = new StringBuilder("requestUrl:" + params.getUri());
        if(params != null){
            strBuild.append("\nrequestParams:");
            List<KeyValue> keyValues = params.getBodysParams();
            for( KeyValue keyValue : keyValues){
                strBuild.append("\n" + keyValue.getKeyStr() + "=" + keyValue.getValueStr());
            }
        }
        Log.i(TAG, strBuild.toString());
    }


    public static class Request{

        private String TAG;
        private HttpMethod httpMethod;
        private String url;
        private RequestParams param;

        public HttpMethod getHttpMethod() {
            return httpMethod;
        }

        public String getUrl() {
            return url;
        }

        public RequestParams getParam() {
            return param;
        }

        public String getTAG() {
            return TAG;
        }

        public Request(String TAG, String url){
            this.TAG = TAG;
            this.url = url;
            httpMethod = HttpMethod.GET;
        }

        public Request(String TAG, RequestParams param){
            this.TAG = TAG;
            this.param = param;
            httpMethod = HttpMethod.POST;
        }

    }



}
