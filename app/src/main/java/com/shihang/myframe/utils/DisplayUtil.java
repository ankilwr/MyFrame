package com.shihang.myframe.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class DisplayUtil {
	public static int dp2px(Context context, float dpValue) {  
		final float scale = context.getResources().getDisplayMetrics().density;  
		return (int) (dpValue * scale + 0.5f);  
	}  

	public static int px2dp(Context context, float pxValue) {  
		final float scale = context.getResources().getDisplayMetrics().density;  
		return (int) (pxValue / scale + 0.5f);  
	}  
	
	public static int getWindowWidth(Activity context){
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
	
	public static int getWindowHeigth(Activity context){
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
	
}
