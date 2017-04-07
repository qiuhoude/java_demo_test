package com.houde.spring.ioc.impl;

import com.houde.spring.ioc.HelloApi;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
//静态工厂类
public class DependencyInjectByStaticFactory {
    public static HelloApi newInstance(String message, int index) {
        return new HelloImpl3(message, index);
    }
}
