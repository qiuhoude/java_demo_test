package com.houde.spring.ioc.impl;

import com.houde.spring.ioc.HelloApi;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HelloApiStaticFactory {

    //工厂方法
    public static HelloApi newInstance(String message) {
        //返回需要的Bean实例
        return new HelloImpl2(message);
    }
}
