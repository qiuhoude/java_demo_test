package com.houde.concurrent.executors_demo;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class Task implements Callable {
    private int order;

    public Task() {
    }

    public Task(int order) {
        this.order = order;
    }

    public Object call() throws Exception {
        System.out.println("普通任务:" + order);
        return null;
    }
}
