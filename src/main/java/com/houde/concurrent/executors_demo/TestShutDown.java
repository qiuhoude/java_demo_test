package com.houde.concurrent.executors_demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class TestShutDown {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        service.submit(new Task(1));
        service.submit(new Task(2));
        service.submit(new LongTask());
        service.submit(new Task(3));
        service.shutdown();
        while (!service.awaitTermination(1, TimeUnit.SECONDS)) {
            //awaitTermination 回等待线程池
            System.out.println("线程池没有关闭");
        }
        System.out.println("线程池已经关闭======close");
    }

}
