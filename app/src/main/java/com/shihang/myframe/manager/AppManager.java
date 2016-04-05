package com.shihang.myframe.manager;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;


public class AppManager {

	private AppManager() {}

	private static List<Activity> activitys = new ArrayList<Activity>();
	private static int resumed, paused;


	public static void addActivity(Activity activity) {
		if (!activitys.contains(activity)) {
			activitys.add(activity);
		}
	}

	public static void removeActivity(Activity activity) {
		if (activitys.contains(activity)) {
			activitys.remove(activity);
		}
	}

	public static void exit() {
		for (Activity activity : activitys) {
			activity.finish();
		}
		System.exit(0);
	}



	public static void onActivityResumed(Activity activity) {
		++resumed;
	}

	public static void onActivityPaused(Activity activity) {
		++paused;
	}

	/** ture:前台(foreground), false : 后台(background) */
	public static boolean appInForeground(){
		//如果
		//可交互(resumed)的activity 
		//大于 
		//暂停(paused)的 activity
		//那么就是在前台,否则在后台
		return resumed > paused;
	}


	public static int getAppVersionCode(Context context){
		PackageManager manager = context.getPackageManager();
		int version = -1;
		try {
			PackageInfo  info = manager.getPackageInfo(context.getPackageName(), 0);
			version = info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}


	public static String getAppVersionName(Context context){
		PackageManager manager = context.getPackageManager();
		String version = null;
		try {
			PackageInfo  info = manager.getPackageInfo(context.getPackageName(), 0);
			version = info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}

	public static String getDeviceID(Context context) {
		String strResult = null;
		TelephonyManager telephonyManager = (TelephonyManager) context .getSystemService(Context.TELEPHONY_SERVICE);
		if (telephonyManager != null) {
			strResult = telephonyManager.getDeviceId();
		}
		if (strResult == null) {
			strResult = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
		}
		return strResult;
	}



	public static boolean hasNetWork(Context context){
		Context mContext = context.getApplicationContext();
		ConnectivityManager connectManager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectManager == null) {
			return false;
		} else {
			NetworkInfo[] networkInfo = connectManager.getAllNetworkInfo();
			if (networkInfo != null && networkInfo.length > 0) {
				for (int i = 0; i < networkInfo.length; i++) {
					//System.out.println(i + "===状态===" + networkInfo[i].getState());
					//System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
					//打印结果
					//0===状态===UNKNOW
					//0===类型===mobile
					//1===状态===CONNECTED
					//1===类型===WIFI
					if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
