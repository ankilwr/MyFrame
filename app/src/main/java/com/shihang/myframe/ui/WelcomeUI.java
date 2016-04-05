package com.shihang.myframe.ui;

import android.os.Bundle;
import android.os.Handler;
import com.shihang.myframe.R;
import com.shihang.myframe.base.BaseUI;
import org.xutils.view.annotation.ContentView;


@ContentView(R.layout.ui_welcome)
public class WelcomeUI extends BaseUI {

    @Override
    public void initViews(Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openActivity(HomeUI.class);
                finish();
            }
        }, 3000);
    }

}
