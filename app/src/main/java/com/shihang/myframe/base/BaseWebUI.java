package com.shihang.myframe.base;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseWebUI extends BaseUI {

    private WebView webView;
    private boolean isLoading;    //是否在加载

    //是否正常的在loading(加载成功失败都属于正常)
    //当onReceivedError跟onReceivedTitle都不执行时不正常:会出错误页面(用于替换自己的错误页面))
    private boolean loadingFlag;

    /** 返回键标记(根据这个标记将loadingFlag初始化)
     (goBack为正常的loading,但不会执行onReceivedError跟onReceivedTitle);
    */
    private boolean backFlag;

    private Map<String, String> titles = new HashMap<>();
    public abstract int getWebViewId();

    public String removeTitle(){
        return titles.remove(webView.getUrl());
    }
    public String getWebTitle(String url){
        return titles.get(url);
    }

    public String getWebTitle(){
        if(webView != null){
            return webView.getTitle();
        }
        return null;
    }

    public boolean isLoading(){
        return isLoading;
    }


    //以下众多方法只为服务于backFlag变量；
    //(goBack的时候不加载title，跟 error方法)
    //backFlag变量用于判断是否是goBack还是正常加载然后出错并不执行(goBack(),error())
    public void goBack(){
        backFlag = true;
        if(webView != null){
            webView.goBack();
        }
    }

    public boolean canGoBack(){
        if(webView != null){
            return webView.canGoBack();
        }
        return false;
    }

    public void reload(){
        if(webView != null){
            webView.reload();
        }
    }

    public String getUrl(){
        if(webView != null){
            return webView.getUrl();
        }
        return null;
    }

    public void loadUrl(String url){
        if(webView != null){
            webView.loadUrl(url);
        }
    }


    public void mReceivedTitle(WebView view, String title){}
    public void mProgressChanged(WebView view, int newProgress){}
    public boolean mShouldOverrideUrlLoading(WebView view, String url){
        return false;
    }
    public void mPageStarted(WebView view, String url, Bitmap favicon){}
    public void mPageFinished(WebView view, String url, boolean loadingSuccess){}

    public void addJavascriptInterface(Object object, String name){
        if(webView != null){
            webView.addJavascriptInterface(object, name);
        }
    }

    public void loadJavascrip(String methodName,ValueCallback<String> resultCallback){
        if(webView != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                webView.evaluateJavascript(methodName, resultCallback);
            }else{
                webView.loadUrl(methodName);
            }
        }
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        webView = (WebView) findViewById(getWebViewId());
        if(webView != null){
            WebSettings settings = webView.getSettings();
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
            settings.setJavaScriptEnabled(true);
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            settings.setAllowFileAccess(true);
            settings.setDomStorageEnabled(true);
            //settings.setAppCachePath(FileUtils.getAppCachePath());
            //settings.setAppCacheEnabled(true);
            webView.setWebViewClient(new WebViewClient(){

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    Log.i(TAG, "shouldOverrideUrlLoading() url:" + url);
                    if(mShouldOverrideUrlLoading(view, url)){
                        loadingFlag = true;
                        return true;
                    }
                    return false;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    Log.i(TAG, "onPageStarted() url:" + url);
                    isLoading = true;
                    if(backFlag){
                        backFlag = false;
                        loadingFlag = true;
                    }else{
                        loadingFlag = false;
                    }
                    mPageStarted(view, url, favicon);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    Log.i(TAG, "onPageFinished() url:" + url);
                    mPageFinished(view, url, isLoading && loadingFlag ? true : false);
                    isLoading = false;
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Log.i(TAG, "onReceivedError() url:" + failingUrl);
                    isLoading = false;
                    loadingFlag = true;
                }
            });
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    Log.i(TAG, "onReceivedTitle() title:" + title);
                    loadingFlag = true;
                    if ("找不到网页".equals(title)) {
                        isLoading = false;
                        title = getWebTitle(webView.getUrl());
                    }else{
                        titles.put(view.getUrl(), title);
                    }
                    mReceivedTitle(view, title);
                }

                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    Log.i(TAG, "onProgressChanged() loading:" + newProgress);
                    mProgressChanged(view, newProgress);
                }
            });
            webView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
        }
    }
}
