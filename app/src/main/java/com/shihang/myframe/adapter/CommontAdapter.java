package com.shihang.myframe.adapter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommontAdapter<T> extends BaseAdapter{
	
	public LayoutInflater inflater;
	public List<T> beans;
	public Context mContext;
	private int mLayoutId;
	
	
	public void addNotify (List<T> addBeans){
		if(addBeans != null && addBeans.size() > 0){
			beans.addAll(addBeans);
		}
		notifyDataSetChanged();
	}
	
	public void resetNotify (List<T> resetBeans){
		beans.clear();
		if(resetBeans != null && resetBeans.size() > 0){
			beans.addAll(resetBeans);
		}
		notifyDataSetChanged();
	}
	
	
	public void cleanNotify (){
		beans.clear();
		notifyDataSetChanged();
	}
	

	public CommontAdapter(Context context,List<T> beans,int layoutId){
		this.mLayoutId = layoutId;
		this.mContext = context;
		inflater = LayoutInflater.from(context);
		this.beans = beans;
	}

	@Override
	public int getCount() {
		return beans.size();
	}

	@Override
	public T getItem(int position) {
		return beans.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = ViewHolder.get(mContext, convertView, parent, mLayoutId, position);
		T bean = getItem(position);
		setConvertView(holder,bean);
		return holder.getConvertView();
	}
	
	public abstract void setConvertView(ViewHolder holder,T bean);

}
