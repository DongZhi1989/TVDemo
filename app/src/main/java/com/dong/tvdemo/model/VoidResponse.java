package com.dong.tvdemo.model;

import java.io.Serializable;

/**
 * Author      : <Dr_dong>
 * Date        : 2018/9/13
 * Description :
 */
public class VoidResponse implements Serializable {

    public String count;

    public BaseResponse toLzyResponse() {
        BaseResponse lzyResponse = new BaseResponse();
        lzyResponse.count = count;
        return lzyResponse;
    }
}
