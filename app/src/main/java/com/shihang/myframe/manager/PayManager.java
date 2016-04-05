package com.shihang.myframe.manager;

import java.util.ArrayList;
import java.util.List;

public class PayManager {
	
	
	public enum PayMethod{
		ALIPAY,
		WECHAT
	}
	
	
	public interface PayResultListener{
		void payResult(boolean result, PayMethod payMethod, String orderOn);
		void paying(String msg);
		void payCancel();
	}

	private static List<PayResultListener> payResults = new ArrayList<>();

	public static void addListener(PayResultListener listener){
		if(listener != null && !payResults.contains(listener)){
			payResults.add(listener);
		}
	}

	public static void removeListener(PayResultListener listener){
		if(listener != null && payResults.contains(listener)){
			payResults.remove(listener);
		}
	}


	public static void pulishPayResult(boolean payResult, PayMethod payMethod, String orderOn){
		for(PayResultListener listener : payResults){
			if(listener != null){
				listener.payResult(payResult, payMethod, orderOn);
			}
		}
	}

	public static void pulishPayCancel(){
		for(PayResultListener listener : payResults){
			if(listener != null){
				listener.payCancel();
			}
		}
	}


	public static void pulishPaying(String orderOn){
		for(PayResultListener listener : payResults){
			listener.paying(orderOn);
		}
	}

}
