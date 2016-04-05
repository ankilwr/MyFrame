package com.shihang.myframe.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class BitmapUtils {

    public static File getFile(Context context, Uri uri) {
        try {
            String filePath = UriUtils.getPath(context, uri);
            return new File(filePath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static int getZoomSize(BitmapFactory.Options options, int needWidth, int needHeight){
        int zoomSize = 1;
        if(options.outWidth > needWidth || options.outHeight > needHeight){
            int widthZoom = Math.round(options.outWidth / needWidth);
            int heightZoom = Math.round(options.outHeight / needHeight);
            zoomSize = widthZoom < heightZoom ? widthZoom : heightZoom;
        }
        return zoomSize;
    }


    public static Bitmap getThumbnail(String filePath, int width, int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 不把图片加载进内存也能计算出大小
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = getZoomSize(options, width, height);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static boolean saveBitmap(String filePath, File saveFile, int width, int height, int quality){
        Bitmap bitmap = getThumbnail(filePath, width, height);
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        //byte[] bitmapByte = baos.toByteArray();
        try {
            OutputStream stream = new FileOutputStream(saveFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
            return true;
        } catch (FileNotFoundException e) {

        }
        return false;
    }
}