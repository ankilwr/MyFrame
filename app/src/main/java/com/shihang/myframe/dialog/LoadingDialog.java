package com.shihang.myframe.dialog;

import android.app.Dialog;
import android.content.Context;
import com.shihang.myframe.R;

public class LoadingDialog extends Dialog {
	
	
	public LoadingDialog(Context context) {
		super(context, R.style.dialog_loading);
		setContentView(R.layout.dialog_loading);
	}
	
	@Override
	public void cancel() {
		super.cancel();
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void dismiss() {
		super.dismiss();
	}

}
