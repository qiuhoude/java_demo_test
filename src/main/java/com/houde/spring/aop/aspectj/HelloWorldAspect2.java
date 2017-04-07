package com.houde.spring.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

//声明切面
@Aspect
public class HelloWorldAspect2 {

    //声明切入点
    @Pointcut(value = "execution(* com.houde.spring.aop..*.sayAdvisorBefore(..))&&args(param)", argNames = "param")
    public void beforePointcut(String param) {
    }

    //定义前置通知
    @Before(value = "beforePointcut(param)", argNames = "param")
    public void beforeAdvice(String param) {
        System.out.println("===========AspectJ before advice param:" + param);
    }

    @AfterReturning(
            value = "execution(*  com.houde.spring.aop..*.sayBefore(..))",
            pointcut = "execution(*  com.houde.spring.aop..*.sayAfterReturning(..))",
            argNames = "retVal", returning = "retVal")
    public void afterReturningAdvice(Object retVal) {
        System.out.println("===========after returning advice retVal:" + retVal);
    }

    @AfterThrowing(
            value = "execution(* com.houde.spring.aop..*.sayAfterThrowing(..))",
            argNames = "exception", throwing = "exception")
    public void afterThrowingAdvice(Exception exception) {
        System.out.println("===========after throwing advice exception:" + exception);
    }

    //后置通知
    @After(value = "execution(* com.houde.spring.aop..*.*(..))")
    public void afterFinallyAdvice() {
        System.out.println("===========after finally advice");
    }

    //环绕通知
    @Around(value = "execution(* com.houde.spring.aop..*.sayAround(..))")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("===========around before advice");
        Object retVal = pjp.proceed(new Object[]{"replace"});
        System.out.println("===========around after advice");
        return retVal;
    }

    @Around(value = "execution(* com.houde.spring.aop..*.sayAround2(..))")
    public Object aroundAdvice2(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("===========around before advice");
        Object retVal = pjp.proceed(new Object[]{"5678"});
        System.out.println("===========around after advice");
        System.out.println(retVal.getClass().getName());
        return retVal;
    }
}
