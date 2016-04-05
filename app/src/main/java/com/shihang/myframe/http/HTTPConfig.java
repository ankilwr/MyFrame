package com.shihang.myframe.http;


public abstract class HTTPConfig {

    //private static final String SUCCESS = "1";

    public static boolean check(String status){
        return status != null && "1".equals(status) ? true : false;
    }

    public static final String PHONE = "13800138000";

    private static final String COM = "http://hulijing.gz10.hunuo.net/";

    //首页
    public static final String HOMEURL = COM + "mobile/index.php";

    //店铺
    public static final String HOME_STORE = COM + "mobile/index.php?act=store_list&op=index";

    //购物车
    public static final String HOME_CART = COM + "mobile/index.php?act=cart&op=index";

    //个人中心
    public static final String HOME_MEMBER = COM + "mobile/index.php?act=member&op=home";

}
