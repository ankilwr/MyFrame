package com.shihang.myframe.manager;

import android.util.SparseArray;

public class FmManager {


	public interface FMVisibleStatus{
		void visible(boolean isVisible);
	}


	public static int mVisibleWeb = -1;
	private static int visibleHasCode;



	private static SparseArray<FMVisibleStatus> fmStatus = new SparseArray<FMVisibleStatus>();


	public static void addFmStatus(int hasCode,FMVisibleStatus visibleListener){
		fmStatus.put(hasCode,visibleListener);
	}


	public static void removeFmStatus(int hasCode){
		fmStatus.remove(hasCode);
	}


	public static int getVisibleHasCode(){
		return visibleHasCode;
	}


	public static void fmVisible(int hasCode){
		if(hasCode == visibleHasCode){
			return;
		}
		FMVisibleStatus listener = fmStatus.get(visibleHasCode);
		if(listener != null){
			listener.visible(false);
		}
		visibleHasCode = hasCode;
		listener = fmStatus.get(visibleHasCode);
		if(listener != null){
			listener.visible(true);
		}
	}

	public static void fmVisible(int hasCode, int visibleWeb){
		mVisibleWeb = visibleWeb;
		FMVisibleStatus listener = fmStatus.get(visibleHasCode);
		if(listener != null){
			listener.visible(false);
		}
		visibleHasCode = hasCode;
		listener = fmStatus.get(visibleHasCode);
		if(listener != null){
			listener.visible(true);
		}
	}

}
