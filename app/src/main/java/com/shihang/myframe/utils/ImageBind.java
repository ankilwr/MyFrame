package com.shihang.myframe.utils;


import org.xutils.image.ImageOptions;

public class ImageBind {

    //头像加载配置
    public static ImageOptions headOptions = new ImageOptions.Builder()
            //.setUseMemCache(false)
            .setCircular(true)
            .build();

}
