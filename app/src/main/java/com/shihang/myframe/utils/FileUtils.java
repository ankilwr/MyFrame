package com.shihang.myframe.utils;


import android.os.Environment;
import android.text.TextUtils;
import org.xutils.x;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;


public class FileUtils {

	private static String sdCache = "cache";
	public static final String IMAGE_CACHE = "xUtils_img_thumb/";

	public static File createFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}


	/** 获取应用程序缓存文件夹  */
	public static File getAppCache() {
		if (hasSdCard()) {
			//context.getExternalCacheDir();
			return x.app().getExternalCacheDir();
		} else {
			//context.getCacheDir();
			return  x.app().getCacheDir();
		}
	}


	public static File createAppCacheFile(String fileName) {
		String path = getAppCache() +  File.separator + fileName;
		return createFile(path);
	}


	public static File getCache(){
		String path;
		if (hasSdCard()) {
			path = Environment.getExternalStorageDirectory() + File.separator + sdCache;
		} else {
			path = Environment.getDataDirectory() + File.separator + sdCache;
		}
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	public static File createCacheFile(String fileName){
		String path = getCache() + File.separator + fileName;
		return createFile(path);
	}


	public static String getTimeName(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
	}


	/**  获取文件夹大小   */
	public static long getFolderSize(File file){
		long size = 0;
		try {
			File[] fileList = file.listFiles();
			for (int i = 0; i < fileList.length; i++){
				if (fileList[i].isDirectory()){//判断子文件是否是文件夹     
					size = size + getFolderSize(fileList[i]);
				}else{
					size = size + fileList[i].length();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return size/1048576; MB   
		return size;
	}

	/** 删除指定目录下文件及目录  (第二个参数为是否要删除该文件夹，false为清空文件夹但不删除) */
	public static boolean deleteFolderFile(String filePath, boolean deleteThisPath) {
		if (!TextUtils.isEmpty(filePath)) {
			try {
				File file = new File(filePath);
				if (file.isDirectory()) {// 处理目录     
					File files[] = file.listFiles();
					for (int i = 0; i < files.length; i++) {
						deleteFolderFile(files[i].getAbsolutePath(), true);
					}
				}
				if (deleteThisPath && (!file.isDirectory() || file.listFiles().length == 0)) {
					// 如果是文件，删除 || 文件夹是空的，删除
					file.delete();
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}


	public static boolean hasSdCard() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}


	/**
	 * 格式化单位
	 * @param size
	 * @return
	 */
	public static String getFormatSize(double size) {
		double kiloByte = size/1024;
		if(kiloByte < 1) {
			return size + "Byte(s)";
		}

		double megaByte = kiloByte/1024;
		if(megaByte < 1) {
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
		}

		double gigaByte = megaByte/1024;
		if(gigaByte < 1) {
			BigDecimal result2  = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
		}

		double teraBytes = gigaByte/1024;
		if(teraBytes < 1) {
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
		}
		BigDecimal result4 = new BigDecimal(teraBytes);
		return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
	}




}
