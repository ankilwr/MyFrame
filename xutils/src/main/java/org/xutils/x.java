package org.xutils;

import android.app.Application;
import android.util.Log;

import org.xutils.common.KeyValue;
import org.xutils.common.TaskController;
import org.xutils.common.task.TaskControllerImpl;
import org.xutils.db.DbManagerImpl;
import org.xutils.http.HttpManagerImpl;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageManagerImpl;
import org.xutils.view.ViewInjectorImpl;

import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;


/**
 * Created by wyouflf on 15/6/10.
 * 任务控制中心, http, image, db, view注入等接口的入口.
 * 需要在在application的onCreate中初始化: x.Ext.init(this);
 */
public final class x {

    private x() {
    }

    public void log(String TAG, String url, RequestParams params){
        if(url != null){
            Log.i(TAG, "request_Url:\nurl=" + url + "\n");
        }
        if(params != null){
            List<KeyValue> list = params.getBodyParams();
            if(list != null){
                StringBuilder param = new StringBuilder();
                param.append("request_Params:\n");
                for(KeyValue nameValue : list ){
                    param.append("params:" + nameValue.getKeyStr() + "=" + nameValue.getValueStr()+"\n");
                }
                Log.i(TAG,param.toString());
            }
        }
    }


    public static boolean isDebug() {
        return Ext.debug;
    }

    public static Application app() {
        if (Ext.app == null) {
            throw new RuntimeException("please invoke x.Ext.init(app) on Application#onCreate()");
        }
        return Ext.app;
    }

    public static TaskController task() {
        return Ext.taskController;
    }

    public static HttpManager http() {
        if (Ext.httpManager == null) {
            HttpManagerImpl.registerInstance();
        }
        return Ext.httpManager;
    }

    public static ImageManager image() {
        if (Ext.imageManager == null) {
            ImageManagerImpl.registerInstance();
        }
        return Ext.imageManager;
    }

    public static ViewInjector view() {
        if (Ext.viewInjector == null) {
            ViewInjectorImpl.registerInstance();
        }
        return Ext.viewInjector;
    }

    public static DbManager getDb(DbManager.DaoConfig daoConfig) {
        return DbManagerImpl.getInstance(daoConfig);
    }

    public static class Ext {
        private static boolean debug;
        private static Application app;
        private static TaskController taskController;
        private static HttpManager httpManager;
        private static ImageManager imageManager;
        private static ViewInjector viewInjector;

        private Ext() {
        }

        static {
            TaskControllerImpl.registerInstance();
            // 默认信任所有https域名
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        }

        public static void init(Application app) {
            if (Ext.app == null) {
                Ext.app = app;
            }
        }

        public static void setDebug(boolean debug) {
            Ext.debug = debug;
        }

        public static void setTaskController(TaskController taskController) {
            if (Ext.taskController == null) {
                Ext.taskController = taskController;
            }
        }

        public static void setHttpManager(HttpManager httpManager) {
            Ext.httpManager = httpManager;
        }

        public static void setImageManager(ImageManager imageManager) {
            Ext.imageManager = imageManager;
        }

        public static void setViewInjector(ViewInjector viewInjector) {
            Ext.viewInjector = viewInjector;
        }
    }
}
