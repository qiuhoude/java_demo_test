package com.houde.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class LongTask implements Callable{
    public Object call() throws Exception {
        System.out.println("长时间任务");
        TimeUnit.SECONDS.sleep(2);
        return null;

    }
}
