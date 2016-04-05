package com.shihang.myframe.utils;

import android.content.Context;
import android.widget.Toast;


public class TShow {

	private TShow(){}

	private static Toast toast;


	public static void showShort(Context context,String msg){
		if(toast == null){
			toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		}else{
			toast.setText(msg);
		}
		toast.show();
	}


//	public static void showShort(Context context,String msg){
//		if(toast == null){
//			toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
//		}else{
//			toast.setText(msg);
//		}
//		View toastBgView = toast.getView();
//		toastBgView.setBackgroundResource(R.drawable.bg_toast);
//		toast.setView(toastBgView);
//		toast.show();
//	}

	public static void showShort(Context context,int msgIds){
		String msg = context.getResources().getString(msgIds);
		if(toast == null){
			toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		}else{
			toast.setText(msg);
		}
		//View toastBgView = toast.getView();
		//toastBgView.setBackgroundResource(R.drawable.bg_toast);
		//toast.setView(toastBgView);
		toast.show();
	}



	public static void showLong(Context context, String msg) {
		if (null == toast) {
			toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}


	public static void showLong(Context context, int msg) {
		if (null == toast) {
			String text = context.getResources().getString(msg);
			toast = Toast.makeText(context, text , Toast.LENGTH_LONG);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}


	public static void showGravityShort(Context context, String msg, int center) {
		if (null == toast) {
			toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
			toast.setGravity(center, 0, 0);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}


	public static void showGravityLong(Context context, String msg, int center) {
		if (null == toast) {
			toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
			toast.setGravity(center, 0, 0);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}

}
