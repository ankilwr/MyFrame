package com.shihang.myframe.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckUtil {


	/**
	 * (phoneNum is null return false)
	 * yes return true; no return false;
	 * @param phoneNum
	 * @return boolean
	 */
	public static boolean isMobile(String phoneNum) {
		if(phoneNum != null){
			Pattern p = Pattern.compile("^((13[0-9])|(14[5|7])|(15[0-9])|(17[0|7|8])|(18[0-9]))\\d{8}$");// \\d{8}代表后面还有8个数字,$表示结束
			Matcher m = p.matcher(phoneNum);
			return m.matches();
		}else{
			return false;
		}
	}

	/**
	 * (carCode is null return false)
	 * yes return true; no return false;
	 * @param carCode
	 * @return boolean
	 */
	public static boolean isCarCode(String carCode) {
		if(carCode != null){
			Pattern p = Pattern.compile("^[\u4E00-\u9FA5][A-Za-z]{1}[A-Za-z_0-9]{5}$");//验证车牌号
			Matcher m = p.matcher(carCode);
			return m.matches();
		}else{
			return false;
		}
	}


	/**
	 * (email is null return false)
	 * yes return true; no return false;
	 * @param email
	 * @return boolean
	 */
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}



	/** true : net Connection;  false: net disconnect */
	public static boolean isConnected(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = conn.getActiveNetworkInfo();
		return (info != null && info.isConnected());
	}


	/** true : s = number | Chinese | English  false: ,.* */
	public static boolean isValidTagAndAlias(String s) {
		Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_-]{0,}$");
		Matcher m = p.matcher(s);
		return m.matches();
	}


	/**
	 * String data is json ; yes return true, no return false;
	 */
	public static boolean isJson(String json) {
		if(json == null){
			return false;
		}
		json = json.trim();
		if(("{".equals(json.substring(0,1))&&"}".equals(json.substring(json.length()-1)))){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * String data is jsonArray ; yes return true, no return false;
	 */
	public static boolean isJsonArray(String json) {
		if(json == null){
			return false;
		}
		json = json.trim();
		if("[".equals(json.substring(0,1))&&"]".equals(json.substring(json.length()-1))){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * To show whether the String is null or is an empty String
	 */
	public static boolean isNullOrNil(String str) {
		return str == null || "".equals(str) || str.length() == 0 ;
	}


	/** APP is not runing true:false  */
	public static boolean isBackground(Context context) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.processName.equals(context.getPackageName())) {
				if (appProcess.importance != RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
					return true; 
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
