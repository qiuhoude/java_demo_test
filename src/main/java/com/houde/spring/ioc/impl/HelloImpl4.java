package com.houde.spring.ioc.impl;

import com.houde.spring.ioc.HelloApi;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HelloImpl4 implements HelloApi {
    private String message;
    private int index;
    private boolean success;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    //setter方法
    public void setMessage(String message) {
        this.message = message;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void sayHello() {
        System.out.println(index + ":" + message + " ; success=" + success);
    }
}
