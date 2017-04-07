package com.houde.spring.aop;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public interface IHelloWorldService {
    public void sayHello(String name);

    public boolean sayAfterReturning();

    public void sayAfterThrowing();

    public boolean sayAfterFinally();

    public void sayAround(String param);


    public void sayAdvisorBefore(String param);
}
