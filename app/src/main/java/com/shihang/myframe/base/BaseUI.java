package com.shihang.myframe.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.shihang.myframe.R;
import com.shihang.myframe.dialog.LoadingDialog;
import com.shihang.myframe.inter.HttpCallBack;
import com.shihang.myframe.inter.HttpLinkCallBack;
import com.shihang.myframe.manager.AppManager;
import com.shihang.myframe.manager.HttpManager;
import com.shihang.myframe.manager.HttpManager.Request;
import com.shihang.myframe.utils.CheckUtil;
import com.shihang.myframe.utils.TShow;
import org.xutils.common.Callback.Cancelable;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.util.List;

public abstract class BaseUI extends AppCompatActivity {


	public String TAG;
	public SparseArray<Cancelable> mHttps = new SparseArray<>();
	private LoadingDialog loadingDialog;

	private ImageView back;
	private TextView title;
	private ImageView rightImage;
	private TextView rightText;

	public abstract void initViews(Bundle savedInstanceState);
	public void rightImageClick(View v){}
	public void rightTextClick(View v){}
	public void leftClick(View v){
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TAG = this.getClass().getSimpleName();
		AppManager.addActivity(this);
		x.view().inject(this);
		back = (ImageView) findViewById(R.id.back);
		title = (TextView) findViewById(R.id.titleText);
		rightText = (TextView) findViewById(R.id.rightText);
		rightImage = (ImageView) findViewById(R.id.rightImage);
		initOnClick();
		initViews(savedInstanceState);
	}

	private void initOnClick(){
		if(back!=null){back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				leftClick(v);
			}
		});}
		if(rightImage!=null){rightImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rightImageClick(v);
			}
		});}
		if(rightText!=null){rightText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rightTextClick(v);
			}
		});}
	}

	public void showBack(boolean visible, int imageResourceId){
		if(back == null){
			return;
		}
		if(visible){
			back.setVisibility(View.VISIBLE);
			back.setClickable(true);
			if(imageResourceId != 0){
				back.setImageResource(imageResourceId);
			}
		}else{
			back.setVisibility(View.INVISIBLE);
			back.setClickable(false);
		}
	}

	public void showRightImage(boolean visible, int imageResourceId){
		if(rightImage == null){
			return;
		}
		if(visible){
			rightImage.setVisibility(View.VISIBLE);
			rightImage.setClickable(true);
			if(imageResourceId != 0){
				rightImage.setImageResource(imageResourceId);
			}
		}else{
			rightImage.setVisibility(View.INVISIBLE);
			rightImage.setClickable(false);
		}
	}

	public void showRightText(boolean visible, String text){
		if(rightText == null){
			return;
		}
		if(visible){
			rightText.setVisibility(View.VISIBLE);
			rightText.setClickable(true);
			rightText.setText(text);
		}else{
			rightText.setVisibility(View.GONE);
			rightText.setClickable(false);
		}
	}

	public void showTitle(boolean visible, String text){
		if(title == null){
			return;
		}
		if(visible){
			title.setVisibility(View.VISIBLE);
			title.setText(text);
		}else{
			title.setVisibility(View.GONE);
		}
	}



	//=========   post请求区     ===================   post请求区     ===================   post请求区     ==========
	//=========   post请求区     ===================   post请求区     ===================   post请求区     ==========
	//=========   post请求区     ===================   post请求区     ===================   post请求区     ==========
	public int addPostRequest(RequestParams params, final HttpCallBack callback) {
		Cancelable http = new HttpManager().POST(TAG, params, new HttpCallBack() {
			@Override
			public void start() {
				callback.start();
			}

			@Override
			public void success(boolean success, String result) {
				callback.success(success, result);
				mHttps.remove(callback.getHttpCode());
			}
		});
		callback.setHttp(http);
		mHttps.put(http.hashCode(), http);
		return http.hashCode();
	}


	public int addLoadingPostRequest(RequestParams params, final HttpCallBack callback) {
		Cancelable http = new HttpManager().POST(TAG, params, new HttpCallBack() {
			@Override
			public void start() {
				showLoadingDialog();
				callback.start();
			}

			@Override
			public void success(boolean success, String result) {
				closeLoadingDialog();
				callback.success(success, result);
				mHttps.remove(callback.getHttpCode());
			}
		});
		callback.setHttp(http);
		mHttps.put(http.hashCode(), http);
		return http.hashCode();
	}

	public int addLoadingFinishPostRequest(RequestParams params, final HttpCallBack callback) {
		Cancelable http = new HttpManager().POST(TAG, params, new HttpCallBack() {
			@Override
			public void start() {
				showLoadingDialog(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						finish();
					}
				});
				callback.start();
			}

			@Override
			public void success(boolean success, String result) {
				closeLoadingDialog();
				callback.success(success, result);
				mHttps.remove(callback.getHttpCode());
			}
		});
		callback.setHttp(http);
		mHttps.put(http.hashCode(), http);
		return http.hashCode();
	}

	public int addLoadingCancelPostRequest(RequestParams params, final HttpCallBack callback) {
		Cancelable http = new HttpManager().POST(TAG, params, new HttpCallBack() {
			@Override
			public void start() {
				showLoadingDialog(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						cancelHTTP(callback.getHttpCode());
					}
				});
				callback.start();
			}

			@Override
			public void success(boolean success, String result) {
				closeLoadingDialog();
				callback.success(success, result);
				mHttps.remove(callback.getHttpCode());
			}
		});
		callback.setHttp(http);
		mHttps.put(http.hashCode(), http);
		return http.hashCode();
	}


	//=========   Get请求区      ===================   Get请求区      ===================   Get请求区      ==========
	//=========   Get请求区      ===================   Get请求区      ===================   Get请求区      ==========
	//=========   Get请求区      ===================   Get请求区      ===================   Get请求区      ==========
	public int addGetRequest(String url, final HttpCallBack callback) {
		Cancelable http = new HttpManager().GET(TAG, url, new HttpCallBack() {
			@Override
			public void start() {
				callback.start();
			}

			@Override
			public void success(boolean success, String result) {
				callback.success(success, result);
				closeLoadingDialog();
				mHttps.remove(callback.getHttpCode());
			}
		});
		callback.setHttp(http);
		mHttps.put(http.hashCode(), http);
		return http.hashCode();
	}

	public int addLoadingFinishGetRequest(String url, final HttpCallBack callback) {
		Cancelable http = new HttpManager().GET(TAG, url, new HttpCallBack() {
			@Override
			public void start() {
				showLoadingDialog(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						finish();
					}
				});
				callback.start();
			}

			@Override
			public void success(boolean success, String result) {
				callback.success(success, result);
				closeLoadingDialog();
				mHttps.remove(callback.getHttpCode());
			}
		});
		callback.setHttp(http);
		mHttps.put(http.hashCode(), http);
		return http.hashCode();
	}


	public int addLoadingCancelGetRequest(String url, final HttpCallBack callback) {
		Cancelable http = new HttpManager().GET(TAG, url, new HttpCallBack() {
			@Override
			public void start() {
				showLoadingDialog(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						cancelHTTP(callback.getHttpCode());
					}
				});
				callback.start();
			}

			@Override
			public void success(boolean success, String result) {
				closeLoadingDialog();
				callback.success(success, result);
				mHttps.remove(callback.getHttpCode());
			}
		});
		callback.setHttp(http);
		mHttps.put(http.hashCode(), http);
		return http.hashCode();
	}

	public int addLoadingGetRequest(String url, final HttpCallBack callback) {
		Cancelable http = new HttpManager().GET(TAG, url, new HttpCallBack() {
			@Override
			public void start() {
				showLoadingDialog();
				callback.start();
			}

			@Override
			public void success(boolean success, String result) {
				closeLoadingDialog();
				callback.success(success, result);
				mHttps.remove(callback.getHttpCode());
			}
		});
		callback.setHttp(http);
		mHttps.put(http.hashCode(), http);
		return http.hashCode();
	}


	//=========   link请求区      ===================   link请求区      ===================   link请求区      ==========
	//=========   link请求区      ===================   link请求区      ===================   link请求区      ==========
	//=========   link请求区      ===================   link请求区      ===================   link请求区      ==========
	public void addLinkRequest(List<Request> requests, @NonNull final HttpLinkCallBack linkCallBack){
		new HttpManager().requestLink(TAG, requests, new HttpLinkCallBack() {

			@Override
			public void start() {
				linkCallBack.start();
			}

			@Override
			public void loading(String requestTag, Cancelable http) {
				mHttps.put(http.hashCode(), http);
				linkCallBack.loading(requestTag, http);
				super.loading(requestTag, http);
			}

			@Override
			public boolean httpResult(boolean success, String TAG, String result) {
				mHttps.remove(getHttpCode());
				return linkCallBack.httpResult(success, TAG, result);
			}

			@Override
			public void finish(String result) {
				linkCallBack.finish(result);
			}
		});
	}

	public void addLoadingCancelLinkRequest(List<Request> requests, @NonNull final HttpLinkCallBack linkCallBack){
		new HttpManager().requestLink(TAG, requests, new HttpLinkCallBack() {
			@Override
			public void start() {
				linkCallBack.start();
				showLoadingDialog(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						cancelHTTP(getHttpCode());
					}
				});
			}

			@Override
			public void loading(String requestTag, Cancelable http) {
				linkCallBack.loading(requestTag, http);
				mHttps.put(http.hashCode(), http);
				super.loading(requestTag, http);
			}

			@Override
			public boolean httpResult(boolean success, String TAG, String result) {
				mHttps.remove(getHttpCode());
				return linkCallBack.httpResult(success, TAG, result);
			}

			@Override
			public void finish(String result) {
				closeLoadingDialog();
			}
		});
	}



	public void cancelHTTP(int hasCode) {
		Cancelable mHttp = mHttps.get(hasCode);
		if (mHttp != null) {
			mHttp.cancel();
			mHttps.remove(hasCode);
		}
	}


	public void cancelHTTP(Cancelable http) {
		if(http == null){
			return;
		}
		Cancelable mHttp = mHttps.get(http.hashCode());
		if (mHttp != null) {
			mHttps.remove(http.hashCode());
		}
		http.cancel();
	}


	/********************************* OpenActivity ***********************/
	public void openActivity(Class<?> pClass) {
		Intent intent = new Intent(this, pClass);
		startActivity(intent);
	}

	public void openActivity(String action, Class<?> pClass) {
		Intent intent = new Intent(this, pClass);
		intent.setAction(action);
		startActivity(intent);
	}

	public void openActivityForResult(Class<?> pClass, int requestCode) {
		Intent intent = new Intent(this, pClass);
		startActivityForResult(intent, requestCode);
	}


	/********************************* Dialog  *******************************/

	public void showLoadingDialog(boolean outCancle, boolean backCancle) {
		if (loadingDialog == null || !loadingDialog.isShowing()) {
			loadingDialog = new LoadingDialog(this);
			loadingDialog.setCanceledOnTouchOutside(outCancle);
			loadingDialog.setCancelable(backCancle);
			loadingDialog.show();
		}
	}

	public void showLoadingDialog() {
		if (loadingDialog == null || !loadingDialog.isShowing()) {
			loadingDialog = new LoadingDialog(this);
			loadingDialog.setCanceledOnTouchOutside(false);
			loadingDialog.setCancelable(false);
			loadingDialog.show();
		}
	}

	public void showLoadingDialog(OnCancelListener backCancleListener) {
		if (loadingDialog == null || !loadingDialog.isShowing()) {
			loadingDialog = new LoadingDialog(this);
			loadingDialog.setCanceledOnTouchOutside(false);
			loadingDialog.setCancelable(true);
			loadingDialog.setOnCancelListener(backCancleListener);
			loadingDialog.show();
		}
	}

	public void closeLoadingDialog() {
		if (loadingDialog != null && loadingDialog.isShowing()) {
			loadingDialog.dismiss();
		}
	}


	public static String getNetContent(String msg) {
		//java.net.UnknownHostException: Unable to resolve host "licong.gz1.hostadm.net": No address associated with hostname
		//Internal Server Error
		if(msg == null){
			return null;
		}
		if (msg.contains("Internal Server Error")) {
			msg = "服务器或请求出现错误~~";
		}else if (msg.contains("java.net.SocketTimeoutException")) {
			msg = "网络连接超时~~"; //网络连接超时
		} else if (msg.contains("java.net.UnknownHostException")) {
			msg = "网络链接异常，请检查网络是否连接";   //找不到url路径,url错误
		} else if(msg.contains("Exception")) {
			msg = "连接服务器失败，请检查网络是否连接~~";  //网络断开
		}
		return msg;
	}


	public void showNetError(String msg) {
		showToast(getNetContent(msg));
	}

	public void showToast(String text) {
		if (!CheckUtil.isNullOrNil(text)) {
			TShow.showShort(this, text);
		}
	}

	public void showToast(@StringRes int string) {
		TShow.showShort(this, getStr(string));
	}

	public String getStr(@StringRes int stringId) {
		return getResources().getString(stringId);
	}

	public String getStr2(@StringRes int stringId, String str) {
		return String.format(getStr(stringId), str);
	}


	@Override
	protected void onResume() {
		AppManager.onActivityResumed(this);
		super.onResume();
	}

	@Override
	protected void onPause() {
		AppManager.onActivityPaused(this);
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppManager.removeActivity(this);
		for (int i = 0; i < mHttps.size(); i++) {
			Cancelable http = mHttps.valueAt(i);
			http.cancel();
		}
		mHttps.clear();
	}

}
