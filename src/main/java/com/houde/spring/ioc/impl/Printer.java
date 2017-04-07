package com.houde.spring.ioc.impl;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class Printer {
    private int counter = 0;
    public void print(String type) {
        System.out.println(type + " printer: " + counter++);
    }
}
