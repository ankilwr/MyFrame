package com.shihang.myframe.view;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


public class HomeTabGroup extends LinearLayout{

	public interface OnCheckedChangeListener{
		void onCheckedChanged(HomeTabButton button, boolean isChecked, int index);
	}


	private List<HomeTabButton> buttons = new ArrayList<>();
	private boolean chooseSwitch;

	public HomeTabGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HomeTabGroup(Context context) {
		super(context);
	}


	private HomeTabButton chooseButton;
	private int choosePosition = -1;
	private OnCheckedChangeListener listener;


	
	public void setOnChangeListener(OnCheckedChangeListener listener, boolean chooseSwitch){
		this.chooseSwitch = chooseSwitch;
		initViews();
		if(listener != null){
			this.listener = listener;
		}
	}
	
	
	public void setHomeButtonCheck(int poistion){
		if(chooseButton != null){
			chooseButton.setCheck(false);
		}
		chooseButton = buttons.get(poistion);
		chooseButton.setCheck(true);
		choosePosition = poistion;
	}

	public int getChoosePosition(){
		return choosePosition;
	}
	
	public void cleanHomeButtonCheck(){
		if(chooseButton != null){
			chooseButton.setCheck(false);
		}
		chooseButton = null;
		choosePosition = -1;
	}
	
	private void initViews(){
		int count = getChildCount();
		for( int i = 0;i<count;i++){
			if( getChildAt(i) instanceof HomeTabButton){
				HomeTabButton homeButton = (HomeTabButton)getChildAt(i);
				if(homeButton.isChecked()){
					chooseButton = homeButton;
					homeButton.setImage(true);
				}else{
					homeButton.setImage(false);
				}
				if(!buttons.contains(homeButton)){
					buttons.add(homeButton);
				}
			}
		}
		initBtnListeners();
	}
	
	
	
	
	private void initBtnListeners(){
		for( int i = 0; i<buttons.size();i++){
			final int index = i;
			final HomeTabButton homeButton = buttons.get(i);
			homeButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(chooseButton != null){
						if(chooseButton.hashCode() != homeButton.hashCode()){
							/*
							if(listener != null){
								listener.onCheckedChanged(chooseButton, false, choosePosition);
							}
							*/
							if(chooseSwitch){
								chooseButton.setCheck(false);
								chooseButton = homeButton;
								choosePosition = index;
								chooseButton.setCheck(true);
							}
							if(listener != null){
								listener.onCheckedChanged(homeButton, true, index);
							}
						}
					}else{
						if(chooseSwitch){
							chooseButton = homeButton;
							chooseButton.setCheck(true);
						}
						if(listener != null){
							listener.onCheckedChanged(homeButton, true, index);
						}
					}
				}
			});
		}
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//initViews();
	}

}
