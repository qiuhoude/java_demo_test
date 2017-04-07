package com.houde.spring.ioc.impl;

import com.houde.spring.ioc.HelloApi;

import java.beans.ConstructorProperties;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HelloImpl3 implements HelloApi {
    private String message;
    private int index;

    //@java.beans.ConstructorProperties({"message", "index"})
    public HelloImpl3(String message, int index) {
        this.message = message;
        this.index = index;
    }

    public void sayHello() {
        System.out.println(index + ":" + message);
    }
}
