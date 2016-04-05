package com.shihang.myframe.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.xutils.x;

public abstract class BaseFm extends Fragment{

	public BaseUI context;
	private View view;
	public abstract int getLayout();
	public abstract void initViews();
	public String TAG = getClass().getSimpleName();

	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		context = (BaseUI) activity;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
		view = inflater.inflate(getLayout(), container,false);
		x.view().inject(this, view);
		initViews();
		return view;
	}


	public void showToast(String toast){
		context.showToast(toast);
	}

	public void showToast(int toastId){
		context.showToast(toastId);
	}

	public String getStr(int strId){
		return context.getStr(strId);
	}


}
