package com.houde.spring.aop;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
@Component("helloWorldService")
public class HelloWorldService implements IHelloWorldService {
    public void sayHello(String name) {
        System.out.println("============Hello World! " + name);
    }

    public boolean sayAfterReturning() {
        System.out.println("============after returning");
        return false;
    }

    public void sayAfterThrowing() {
        System.out.println("============before throwing");
        throw new NullPointerException("你大爷空指针了");
    }

    public boolean sayAfterFinally() {
        System.out.println("============before finally");
        throw new RuntimeException();
    }

    public void sayAround(String param) {
        System.out.println("============around param:" + param);
    }

    public void sayAdvisorBefore(String param) {
        System.out.println("============say " + param);
    }

    public String sayAround2(String param) {
        System.out.println("============say " + param);
        return "1234";    }
}
