package com.shihang.myframe.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.text.SpannableStringBuilder;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.shihang.myframe.R;
import com.shihang.myframe.utils.DisplayUtil;

public class MessageDialog extends Dialog {

	private View editLayout;
	private TextView message;
	private TextView title;
	private View confirm;


	public MessageDialog(Activity context){
		super(context);
	editLayout = LayoutInflater.from(context).inflate(R.layout.dialog_message, null);
	message = (TextView)editLayout.findViewById(R.id.text);
	title = (TextView)editLayout.findViewById(R.id.title);
	confirm = editLayout.findViewById(R.id.confirm);
	editLayout.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			dismiss();
		}
	});
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	//设置布局的宽度为窗口的宽度
	DisplayMetrics metric = new DisplayMetrics();
	context.getWindowManager().getDefaultDisplay().getMetrics(metric);
	editLayout.setMinimumWidth(metric.widthPixels - DisplayUtil.dp2px(context, 60));
	setCanceledOnTouchOutside(false);
	setContentView(editLayout);
}

	public MessageDialog setTitle(String titleStr){
		title.setText(titleStr);
		return this;
	}

	public MessageDialog setConfirmListener(final View.OnClickListener listener){
		confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onClick(v);
				dismiss();
			}
		});
		return this;
	}

	public MessageDialog setMessage(String messageStr) {
		message.setText(messageStr);
		return this;
	}

	public MessageDialog setMessage(SpannableStringBuilder messageStr) {
		message.setText(messageStr);
		return this;
	}

}
