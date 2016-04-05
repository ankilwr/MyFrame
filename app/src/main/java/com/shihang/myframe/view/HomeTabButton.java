package com.shihang.myframe.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.shihang.myframe.R;

public class HomeTabButton extends TextView{



	public interface OnCheckedChangeListener {
		 void onCheckedChanged(HomeTabButton button, boolean isChecked);
	}



	private boolean myCheck;
	private boolean match_parent;
	private Drawable top_on;
	private Drawable top_off;
	private Drawable left_on;
	private Drawable left_off;
	private Drawable right_on;
	private Drawable right_off;
	private Drawable bottom_on;
	private Drawable bottom_off;
	private int textColor_off;
	private int textColor_on;
	private Drawable background_off;
	private Drawable background_on;
	private OnCheckedChangeListener checkListener;


	public boolean isChecked(){
		return myCheck;
	}


	public void setCheck(boolean isChecked){
		this.myCheck = isChecked;
		setImage(isChecked);
		if(checkListener != null){
			checkListener.onCheckedChanged(this, isChecked);
		}
	}

	public HomeTabButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	public HomeTabButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public HomeTabButton(Context context) {
		super(context);
		init(null);
	}


	public void setOnChangeListener(OnCheckedChangeListener listener){
		this.checkListener = listener;
	}


	private void init(AttributeSet attrs) {
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.HomeTabButton);
		top_on = a.getDrawable(R.styleable.HomeTabButton_top_on);
		top_off = a.getDrawable(R.styleable.HomeTabButton_top_off);
		left_on = a.getDrawable(R.styleable.HomeTabButton_left_on);
		left_off = a.getDrawable(R.styleable.HomeTabButton_left_off);
		right_on = a.getDrawable(R.styleable.HomeTabButton_right_on);
		right_off = a.getDrawable(R.styleable.HomeTabButton_right_off);
		bottom_on = a.getDrawable(R.styleable.HomeTabButton_bottom_on);
		bottom_off = a.getDrawable(R.styleable.HomeTabButton_bottom_off);
		textColor_on = a.getColor(R.styleable.HomeTabButton_textColor_on, Color.WHITE);
		textColor_off = a.getColor(R.styleable.HomeTabButton_textColor_off,Color.WHITE);
		background_on = a.getDrawable(R.styleable.HomeTabButton_background_on);
		background_off = a.getDrawable(R.styleable.HomeTabButton_background_off);
		myCheck = a.getBoolean(R.styleable.HomeTabButton_checked, false);
		match_parent = a.getBoolean(R.styleable.HomeTabButton_match_parent,false);
		a.recycle();
		setImage(isChecked());
	}


	public void setImage(boolean check){
		if(check){
			if(background_on != null){setBackgroundDrawable(background_on);}
			if(textColor_on != 0){setTextColor(textColor_on);}
			setCompoundDrawablesWithIntrinsicBounds(left_on,top_on,right_on,bottom_on);
		}else{
			if(background_off != null){setBackgroundDrawable(background_off);}
			if(textColor_off != 0){setTextColor(textColor_off);}
			setCompoundDrawablesWithIntrinsicBounds(left_off,top_off,right_off,bottom_off);
		}
	}



	@Override
	protected void onDraw(Canvas canvas) {
		if(!match_parent){
			Drawable[] drawables = getCompoundDrawables();
			if (drawables != null && drawables[0] != null || drawables[2] != null) {

				Drawable drawableLeft = drawables[0];
				Drawable drawableRight = drawables[2];

				float textWidth = getPaint().measureText(getText().toString());

				int drawablePadding = getCompoundDrawablePadding();
				int drawableWidth = 0;

				if(drawableRight != null){
					drawableWidth = drawableRight.getIntrinsicWidth();
				}else if(drawableLeft != null){
					drawableWidth = drawableLeft.getIntrinsicWidth();
				}

				float bodyWidth = textWidth + drawableWidth + drawablePadding;

				if(drawableRight != null){
					setPadding(getPaddingLeft(), getPaddingTop(), (int)(getWidth() - bodyWidth), getPaddingBottom());
				}else if(drawableLeft != null){
					setPadding((int)(getWidth() - bodyWidth), getPaddingTop(), getPaddingRight(), getPaddingBottom());
				}
				canvas.translate((bodyWidth - getWidth()) / 2, 0);
			}
			setGravity(getGravity());
		}
		super.onDraw(canvas);
	}



}
