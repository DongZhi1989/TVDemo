package com.dong.tvdemo.utils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Author      : <Dr_dong>
 * Date        : 2018/9/13
 * Description :
 */
public class GsonUtils {

    private static final Gson gson = new Gson();

    /**
     * Json string 转bean
     */
    public static <T> T parse(String str, Class<T> clazz) {
        if (str == null) {
            return null;
        }
        try {
            return gson.fromJson(str, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Json string 转集合<Bean>
     */
    public static <T> T parse(String str, Type type) {
        if (str == null) {
            return null;
        }
        try {
            return gson.fromJson(str, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSONObject 转bean
     */
    public static <T> T parse(JSONObject json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return parse(json.toString(), clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSONObject 转集合<Bean>
     */
    public static <T> T parse(JSONObject json, Type type) {
        if (json == null) {
            return null;
        }
        try {
            return parse(json.toString(), type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bean转json string
     */
    public static String parseString(Object obj) {
        if (obj == null) {
            return null;
        }
        return gson.toJson(obj);
    }

    public static <T> T parse(JsonReader reader, Type type) throws JsonIOException, JsonSyntaxException {
        return gson.fromJson(reader, type);
    }
}
