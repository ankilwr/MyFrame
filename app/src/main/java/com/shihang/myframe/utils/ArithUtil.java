package com.shihang.myframe.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ArithUtil {

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	// 该类不允许被实例化
	private ArithUtil() {}

	
	/**
	 * 提供精确的加法运算
	 */
	public static double add(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 提供精确的加法运算
	 */
	public static String add(String v1, String v2, int scale) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return round(b1.add(b2).doubleValue(),scale);
	}
	
	
	/**
	 * 提供精确的加法运算
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 提供精确的加法运算
	 */
	public static String add(double v1, double v2, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return round(b1.add(b2).doubleValue(),scale);
	}
	
	
	/**
	 * 提供精确的乘法运算
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	
	
	/**
	 * 提供精确的乘法运算
	 */
	public static double mul(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).doubleValue();
	}
	
	
	/**
	 * 提供精确的乘法运算
	 */
	public static String mul(double v1, double v2, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return round(b1.multiply(b2).doubleValue(),scale);
	}
	
	
	
	/**
	 * 提供精确的乘法运算
	 */
	public static String mul(String v1, String v2, int scale) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return round(b1.multiply(b2).doubleValue(),scale);
	}
	
	
	

	/**
	 * 提供精确的减法运算
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 提供精确的减法运算
	 */
	public static double sub(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).doubleValue();
	}
	

	/**
	 * 提供(相对)精确的除法运算,当发生除不尽的情况的时候,精确到小数点后10位,后面的四舍五入
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度,后面的四舍五入
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精度错误,传入的精度必须为int型参数且必须大于等于0");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public static String round(double v1 , int scale){
		if(scale <= 0){
			scale = 1;
		}
		//StringBuilder buString = new StringBuilder("#.");  //用#的话当0.10这样的小数将会简化成.10
		StringBuilder buString = new StringBuilder("0.");
		for(int i = 0;i < scale ; i++){
			buString.append("0");
		}
		DecimalFormat df = new DecimalFormat(buString.toString());
		//不能返回double,Double.parseDouble("2.00")转换成2.0
		//return Double.parseDouble(df.format(v1));
		return df.format(v1);
	}
	
	
	public static String round(String v1 , int scale){
		if(scale <= 0){
			scale = 1;
		}
		//StringBuilder buString = new StringBuilder("#.");  //用#的话当0.10这样的小数将会简化成.10
		StringBuilder buString = new StringBuilder("0.");
		for(int i = 0;i < scale ; i++){
			buString.append("0");
		}
		DecimalFormat df = new DecimalFormat(buString.toString());
		//不能返回double,Double.parseDouble("2.00")转换成2.0
		//return Double.parseDouble(df.format(v1));
		return df.format(Double.parseDouble(v1));
	}
	
}
