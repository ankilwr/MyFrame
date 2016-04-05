package com.shihang.myframe.manager;

import android.os.Handler;
import android.widget.TextView;

public class CodeManager {

	private TextView button;
	private Handler handler = new Handler();
	private boolean isRuning = false;
	private int count = -1;
	private String textOff;
	private String textOn;

	public CodeManager(String textOn, String textOff, TextView button){
		this.button = button;
		this.textOn = textOn;
		this.textOff = textOff;
	}

	public void startCountDown(){
		isRuning = true;
		count = 60;
		handler.post(new Runnable() {
			@Override
			public void run() {
				if(isRuning){
					count-- ;
					if(count > 0){
						button.setEnabled(false);
						button.setText(String.format(textOn, count + ""));
						handler.postDelayed(this, 1000);
					}else{
						reset();
					}
				}
			}
		});
	}

	public void reset(){
		isRuning = false;
		count = -1;
		button.setEnabled(true);
		button.setText(textOff);
	}

	public void stopCountDown(){
		isRuning = false;
		count = -1;
	}

}
