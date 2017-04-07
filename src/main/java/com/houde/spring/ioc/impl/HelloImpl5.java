package com.houde.spring.ioc.impl;

import com.houde.spring.ioc.HelloApi;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public abstract class HelloImpl5 implements HelloApi {
    private Printer printer;

    public void sayHello() {
        printer.print("setter");
        createPrototypePrinter().print("prototype");
    }

    public abstract Printer createPrototypePrinter();

    public Printer createSingletonPrinter() {
        System.out.println("该方法不会被执行，如果输出就错了");
        return new Printer();
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
