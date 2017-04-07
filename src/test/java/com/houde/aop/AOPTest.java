package com.houde.aop;

import com.houde.spring.aop.HelloWorldService;
import com.houde.spring.aop.IHelloWorldService;
import com.houde.spring.aop.IIntroductionService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class AOPTest {

    @Test
    public void testHelloworld() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("aopAppContext.xml");
        IHelloWorldService helloworldService =
                ctx.getBean("helloWorldService", IHelloWorldService.class);
        helloworldService.sayHello("qiu");
    }


    @Test
    public void testSchemaAfterReturningAdvice() {
        System.out.println("======================================");
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("aopAppContext.xml");
        IHelloWorldService helloworldService =
                ctx.getBean("helloWorldService", IHelloWorldService.class);
        helloworldService.sayAfterReturning();
        System.out.println("======================================");
    }


    @Test(expected = NullPointerException.class)
    public void testSchemaAfterThrowingAdvice() {
        System.out.println("======================================");
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("aopAppContext.xml");
        IHelloWorldService helloworldService =
                ctx.getBean("helloWorldService", IHelloWorldService.class);
        helloworldService.sayAfterThrowing();
        System.out.println("======================================");
    }


    @Test(expected = RuntimeException.class)
    public void testSchemaAfterFinallyAdvice() {
        System.out.println("======================================");
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("aopAppContext.xml");
        IHelloWorldService helloworldService =
                ctx.getBean("helloWorldService", IHelloWorldService.class);
        helloworldService.sayAfterFinally();
        System.out.println("======================================");
    }


    @Test
    public void testSchemaAroundAdvice() {
        System.out.println("======================================");
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("aopAppContext.xml");
        IHelloWorldService helloworldService =
                ctx.getBean("helloWorldService", IHelloWorldService.class);
        helloworldService.sayAround("haha");
        System.out.println("======================================");
    }


    @Test
    public void testSchemaIntroduction() {
        System.out.println("======================================");
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("aopAppContext.xml");
        IIntroductionService introductionService =
                ctx.getBean("helloWorldService", IIntroductionService.class);
        introductionService.induct();
        System.out.println("======================================");
    }


    @Test
    public void testAnnotationBeforeAdvice() {
        System.out.println("======================================");
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("aspectjAppContext.xml");
        HelloWorldService helloworldService =
                ctx.getBean("helloWorldService", HelloWorldService.class);
        helloworldService.sayAdvisorBefore("before");
        System.out.println("======================================");
    }


    @Test
    public void testAspectJAroundAdvice2() {
        System.out.println("======================================");
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("aspectjAppContext.xml");
        HelloWorldService helloworldService =
                ctx.getBean("helloWorldService", HelloWorldService.class);
        helloworldService.sayAround2("hahaxx");
        System.out.println("======================================");
    }
}
