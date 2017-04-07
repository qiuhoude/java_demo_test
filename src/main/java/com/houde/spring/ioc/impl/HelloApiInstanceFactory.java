package com.houde.spring.ioc.impl;

import com.houde.spring.ioc.HelloApi;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HelloApiInstanceFactory {
    public HelloApi newInstance(String message) {
        return new HelloImpl2(message);
    }

    public HelloApi newInstance1() {
        return new HelloImpl();
    }
}
