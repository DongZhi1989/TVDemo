package com.dong.tvdemo.net;

import com.dong.tvdemo.utils.LogUtils;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Author      : <Dr_dong>
 * Date        : 2018/9/13
 * Description :
 */
public abstract class OkGoJsonCallback<T> implements Callback<T> {

    private Type type;
    private Class<T> clazz;

    public OkGoJsonCallback() {
    }

    public OkGoJsonCallback(Type type) {
        this.type = type;
    }

    public OkGoJsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public abstract void netError(com.lzy.okgo.model.Response<T> response);

    public abstract void serversError(com.lzy.okgo.model.Response<T> response);

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (!NetworkUtils.isAvailable()) {
            LogUtils.w("网络异常");
        }
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
    }

    @Override
    public void onCacheSuccess(com.lzy.okgo.model.Response<T> response) {

    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        LogUtils.w("Net", "===onError===", response.getException());
        if (response.getRawResponse() == null) {
            netError(response);
        } else {
            serversError(response);
        }
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void uploadProgress(Progress progress) {

    }

    @Override
    public void downloadProgress(Progress progress) {

    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertResponse(Response response) throws Throwable {

        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用

        //详细自定义的原理和文档，看这里： https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback

        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConvert<T> convert = new JsonConvert<>(clazz);
                return convert.convertResponse(response);
            }
        }

        JsonConvert<T> convert = new JsonConvert<>(type);
        return convert.convertResponse(response);
    }
}
