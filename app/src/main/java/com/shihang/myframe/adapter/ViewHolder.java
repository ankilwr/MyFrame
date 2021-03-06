package com.shihang.myframe.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewHolder {
	
	private SparseArray<View> mViews;
	private int mPosition;
	private View mConertView;
	
	public ViewHolder(Context context,ViewGroup parent,int layoutId,int position){
		this.mPosition = position;
		this.mViews = new SparseArray<View>();
		mConertView = LayoutInflater.from(context).inflate(layoutId, parent,false);
		mConertView.setTag(this);
	}
	
	public static ViewHolder get(Context context,View convertView,ViewGroup parent,int layoutId,int position){
		if(convertView == null){
			return new ViewHolder(context,parent,layoutId,position);
		}else{
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}
	
	public int getPosition(){
		return mPosition; 
	}
	
	
	public <T extends View> T getView(int viewId){
		View view = mViews.get(viewId);
		if(view == null){
			view = mConertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T)view;
	}
	
	public View getConvertView(){
		return mConertView;
	}

}
