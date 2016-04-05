package com.shihang.myframe.utils;

import java.security.MessageDigest;

public class MD5Util {


	/** 32位大写
	 * 32 Big
	 */
	public static String getMd5ValueBig(String value){
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		try {
			byte[] btInput = value.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			 /* .toUpperCase();
			    .toLowerCase();    */
			return new String(str).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/** 32位小写
	 * 32 Small
	 */
	public static String getMd5ValueSmall(String value){
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		try {
			byte[] btInput = value.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			 /* .toUpperCase();
			    .toLowerCase();    */
			return new String(str).toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
