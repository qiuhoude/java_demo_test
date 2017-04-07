package com.houde.spring.ioc.impl;

import com.houde.spring.ioc.HelloApi;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class HelloApiDecorator {
    private HelloApi helloApi;

    public HelloApiDecorator() {
    }

    public HelloApiDecorator(HelloApi helloApi) {
        this.helloApi = helloApi;
    }

    public HelloApi getHelloApi() {
        return helloApi;
    }

    public void setHelloApi(HelloApi helloApi) {
        this.helloApi = helloApi;
    }
}
