package com.dong.tvdemo;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.dong.tvdemo.net.NetworkUtils;
import com.dong.tvdemo.utils.ImageLoader;
import com.dong.tvdemo.utils.LogUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Author      : <Dr_dong>
 * Date        : 2018/9/11
 * Description :
 */
public class AppManager extends Application {

    private static final long SIZE_OF_HTTP_CACHE = 10 * 1024 * 1024;
    private static Context mContext;
    private static OkHttpClient mHttpClient;
    private static Gson mGson;

    public static Resources getResource() {
        return mContext.getResources();
    }

    public static Context getContext() {
        return mContext;
    }

    public static OkHttpClient getHttpClient() {
        return mHttpClient;
    }

    public static Gson getGson() {
        return mGson;
    }

    public static String getIMEI() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            String IMEI_Number = telephonyManager.getDeviceId();
            if ((IMEI_Number != null) && (IMEI_Number.length() > 0)) {
                return IMEI_Number;
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    public static boolean checkPermission(Context paramContext, String paramString) {
        return paramContext.checkCallingOrSelfPermission(paramString) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isNetworkMobile() {
        ConnectivityManager conMan = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMan.getNetworkInfo(0) != null) {
            final NetworkInfo.State mobile = conMan.getNetworkInfo(0).getState();
            if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
                return true;
            else
                return false;
        } else
            return false;
    }

    public static boolean isNetworkWifi() {
        ConnectivityManager conMan = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMan.getNetworkInfo(1) != null) {
            final NetworkInfo.State wifi = conMan.getNetworkInfo(1).getState();
            if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
                return true;
            else
                return false;
        } else
            return false;
    }

    /**
     * 读取application 节点  meta-data 信息
     */
    public static String buildChannel() {
        try {
            ApplicationInfo appInfo = mContext.getPackageManager()
                    .getApplicationInfo(mContext.getPackageName(),
                            PackageManager.GET_META_DATA);
            String mTag = appInfo.metaData.getString("UMENG_CHANNEL");
            return mTag;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        LogUtils.setLogDebug(BuildConfig.DEBUG);
        //初始化网络工具
        initOkGo();
        NetworkUtils.getInstance(mContext);
        //初始化图片加载工具
        ImageLoader.getInstance(mContext);
        mHttpClient = new OkHttpClient();
        mGson = new Gson();
        initHttpClient(mHttpClient, mContext);
    }

    /**
     * 初始化OkGo
     */
    private void initOkGo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //全局的读取超时时间
        builder.readTimeout(5, TimeUnit.MINUTES);
        //全局的写入超时时间
        builder.writeTimeout(10, TimeUnit.MINUTES);
        //全局的连接超时时间
        builder.connectTimeout(10, TimeUnit.MINUTES);
        // 信任所有
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        if (BuildConfig.DEBUG) {
            // 日志拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("NET_INFO");
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
            loggingInterceptor.setColorLevel(Level.INFO);
            builder.addInterceptor(loggingInterceptor);
        }
        //TODO cookie 存储选择
        if (BuildConfig.DEBUG) {
            //使用sp保持cookie，如果cookie不过期，则一直有效
            builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
        } else {
            //使用内存保持cookie，app退出后，cookie消失
            builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
        }

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheMode(CacheMode.NO_CACHE)
                //全局统一缓存时间，默认永不过期，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .setRetryCount(3);
        //全局公共头
        //.addCommonHeaders(headers)
        //全局公共参数
        //.addCommonParams(params)
    }

    private void initHttpClient(OkHttpClient client, Context context) {
        File httpCacheDirectory = context.getCacheDir();
        Cache httpResponseCache = new Cache(httpCacheDirectory, SIZE_OF_HTTP_CACHE);
        //        try {
        //            httpResponseCache =
        //        } catch (IOException e) {
        //            Log.e("Retrofit", "Could not create http cache", e);
        //        }
        //        client.setCache(httpResponseCache);
    }

}
