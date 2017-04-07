package com.houde.spring.ioc.impl;

import com.houde.spring.ioc.HelloApi;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HelloImpl implements HelloApi {

    public void sayHello() {
        System.out.println("Hello World!");
    }
}
