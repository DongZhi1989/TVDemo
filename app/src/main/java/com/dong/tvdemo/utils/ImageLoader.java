package com.dong.tvdemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dong.tvdemo.R;

/**
 * Author      : <Dr_dong>
 * Date        : 2018/9/13
 * Description :
 */
public class ImageLoader {

    private static final String TAG = ImageLoader.class.getSimpleName();
    private static ImageLoader instance;
    private static Context context;

    private ImageLoader(Context context) {
        ImageLoader.context = context;
    }

    public static ImageLoader getInstance(Context context) {
        if (context == null) {
            Log.e(TAG, "Context is null", new NullPointerException());
            return null;
        }
        if (instance == null) {
            instance = new ImageLoader(context);
        }
        return instance;
    }

    public static Context getContext() {
        if (ImageLoader.context == null) {
            Log.e(TAG, "ImageLoader cannot be instantiated", new UnsupportedOperationException());
        }
        return ImageLoader.context;
    }

    /**
     * 获取随机颜色资源
     */
    public static int getRandomRes() {
        return R.color.load_image_color;
    }

    /**
     * @param resId      本地图片资源id
     * @param url        网络图片地址
     * @param iv         显示图片控件
     * @param skipCache  是否跳过内存缓存
     * @param resourceId
     * @param width
     * @param height
     */
    public static void display(@NonNull int resId, String url, @NonNull ImageView iv, @NonNull boolean skipCache,
                               @NonNull int resourceId, @NonNull int width, @NonNull int height) {
        RequestBuilder request;
        if (resId != 0 && url == null) {
            request = Glide.with(getContext()).load(resId);
        } else if (resId == 0 && url != null) {
            request = Glide.with(getContext()).load(url);
        } else {
            return;
        }
        RequestOptions options = new RequestOptions();
        if (resourceId != 0) {
            options.placeholder(resourceId).error(resourceId);
        }
        if (width != 0 && height != 0) {
            options.override(width, height);
        }
        if (skipCache) {
            options.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(skipCache);
        }
        request.apply(options).into(iv);
    }

    public static void display(String url, ImageView iv) {
        display(url, iv, false);
    }

    public static void display(String url, ImageView iv, boolean skipCache) {
        display(0, url, iv, skipCache, getRandomRes(), 0, 0);
    }

    public static void display(int resId, ImageView iv) {
        display(resId, iv, false);
    }

    public static void display(int resId, ImageView iv, boolean skipCache) {
        display(resId, null, iv, skipCache, getRandomRes(), 0, 0);
    }

    public static void display(String url, ImageView iv, int width, int height) {
        display(url, iv, false, width, height);
    }

    public static void display(String url, ImageView iv, boolean skipCache, int width, int height) {
        display(0, url, iv, skipCache, getRandomRes(), width, height);
    }

    public static void display(int resId, ImageView iv, int width, int height) {
        display(resId, iv, false, width, height);
    }

    public static void display(int resId, ImageView iv, boolean skipCache, int width, int height) {
        display(resId, null, iv, skipCache, getRandomRes(), width, height);
    }

    public interface LoaderListener {
        void onSuccess(Bitmap bitmap);
    }
}
