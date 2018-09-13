package com.dong.tvdemo.model;

import java.io.Serializable;

/**
 * Author      : <Dr_dong>
 * Date        : 2018/9/13
 * Description :
 */
public class BaseResponse<T> implements Serializable {

    public String count;
    public T data;

    @Override
    public String toString() {
        return "LzyResponse{\n" +//
                "\tmsg='" + count + "\'\n" +//
                "\tdata=" + data + "\n" +//
                '}';
    }
}
