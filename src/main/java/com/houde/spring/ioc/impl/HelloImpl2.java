package com.houde.spring.ioc.impl;

import com.houde.spring.ioc.HelloApi;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HelloImpl2 implements HelloApi {
    private String message;

    public HelloImpl2() {
        this.message = "Hello World!";
    }

    public HelloImpl2(String message) {
        this.message = message;
    }


    public void sayHello() {
        System.out.println(message);
    }
}
