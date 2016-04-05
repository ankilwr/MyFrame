package com.shihang.myframe.ui;


import android.os.Bundle;
import android.os.Handler;
import com.shihang.myframe.R;
import com.shihang.myframe.base.BaseUI;
import com.shihang.myframe.inter.HttpCallBack;
import com.shihang.myframe.manager.AppManager;
import com.shihang.myframe.view.HomeTabButton;
import com.shihang.myframe.view.HomeTabGroup;
import com.shihang.myframe.view.HomeTabGroup.OnCheckedChangeListener;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.ui_home)
public class HomeUI extends BaseUI {

    private boolean isExit;
    private Handler handler = new Handler();

    @ViewInject(R.id.tabGroup)
    HomeTabGroup tabGroup;


    private OnCheckedChangeListener checkChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(HomeTabButton button, boolean isChecked, int index) {

        }
    };


    @Override
    public void onBackPressed() {
        if(isExit){
            AppManager.exit();
        }else{
            isExit = true;
            showToast("再按一次退出");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        }
    }


    @Override
    public void initViews(Bundle savedInstanceState) {
        tabGroup.setOnChangeListener(checkChangeListener, true);
        String url = "http://api.map.baidu.com/geosearch/v3/nearby?location=113.28599,23.121976&radius=10000&sortby=distance:1&tags=肯德基";


        addLoadingCancelGetRequest(url, new HttpCallBack() {
            @Override
            public void success(boolean success, String result) {
                if (success) {
                    showToast(result);
                } else {
                    showToast("获取数据失败");
                }
            }
        });


          //get请求
          //addGetRequest();
          //addLoadingCancelGetRequest();
          //addLoadingFinishGetRequest();

          //Post请求
          //addPostRequest();
          //addLoadingCancelPostRequest();
          //addLoadingFinishPostRequest();

//        RequestParams params = new RequestParams("http://api.map.baidu.com/geosearch/v3/nearby");
//        params.addBodyParameter("location", "113.28599,23.121976");
//        params.addBodyParameter("radius", "10000");
//        params.addBodyParameter("sortby", "distance:1");
//        params.addBodyParameter("tags", "肯德基");
//        addLoadingCancelPostRequest(params, new HttpCallBack() {
//            @Override
//            public void success(boolean success, String result) {
//                if (success) {
//                    showToast(result);
//                } else {
//                    showToast("获取数据失败");
//                }
//            }
//        });




    }


}
