package com.shihang.myframe.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.graphics.Paint;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

public class CommonUtil {

	public static SpannableStringBuilder getColorText(String content,String colorStr,int color){
		String text = content + colorStr;
		int myStart = text.indexOf(colorStr);
		SpannableStringBuilder builder = new SpannableStringBuilder(text);  
		ForegroundColorSpan oneSpan = new ForegroundColorSpan(color);  
		builder.setSpan(oneSpan, myStart, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); 
		return builder;
	}

	public static SpannableStringBuilder getColorText(String colorStr1,String colorStr2,int color1, int color2){
		String text = colorStr1 + colorStr2;
		SpannableStringBuilder builder = new SpannableStringBuilder(text);
		ForegroundColorSpan oneSpan = new ForegroundColorSpan(color1);
		ForegroundColorSpan twoSpan = new ForegroundColorSpan(color2);
		int myStart = text.indexOf(colorStr2);
		builder.setSpan(oneSpan, 0, colorStr1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		builder.setSpan(twoSpan, myStart, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return builder;
	}

	public static SpannableStringBuilder getColorText(String text, int start, int end, int color){
		SpannableStringBuilder builder = new SpannableStringBuilder(text);
		ForegroundColorSpan span = new ForegroundColorSpan(color);
		builder.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return builder;
	}

	
	public static String time(long timestamp){
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
		return date;
	}

	public static String time(long timestamp, String format){
		String date = new SimpleDateFormat(format).format(new Date(timestamp));
		return date;
	}


	public static void editSetLastLength(EditText edit){
		edit.postInvalidate();
		CharSequence charSequence = edit.getText();
		if (charSequence instanceof Spannable) {
			Spannable spanText = (Spannable) charSequence;
			Selection.setSelection(spanText, charSequence.length());
		}
	}
	
	
	public static void textLineStrike(TextView textView){
		textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		textView.getPaint().setAntiAlias(true);//抗锯齿
	}

	public static void textLineUnder(TextView textView){
		textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		textView.getPaint().setAntiAlias(true);//抗锯齿
	}



	public static List<String> getStringList(String str) {
		if (str == null || str.equals("")) {
			return null;
		}
		List<String> subStrs = new ArrayList<String>();
		int indexOfComma = str.indexOf(',');
		String subStr;
		while (indexOfComma != -1) {
			subStr = str.substring(0, indexOfComma);
			subStrs.add(subStr);
			str = str.substring(indexOfComma + 1);
			indexOfComma = str.indexOf(',');
		}

		subStrs.add(str);
		return subStrs;
	}

	public static List<String> getStringList2(String... strings) {
		if (strings == null || strings.length == 0) {
			return null;
		}
		List<String> subStrs = new ArrayList<String>();
		for(String str : strings){
			subStrs.add(str);
		}
		return subStrs;
	}

}
