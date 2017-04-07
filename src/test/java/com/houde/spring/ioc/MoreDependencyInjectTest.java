package com.houde.spring.ioc;

import com.houde.spring.ioc.impl.DependentBean;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class MoreDependencyInjectTest {

    @Test
    public void testDependOn()   {
//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext("applicationContext.xml");
//        //一点要注册销毁回调，否则我们定义的销毁方法不执行
//        context.registerShutdownHook();
//        DependentBean dependentBean =
//                context.getBean("dependentBean", DependentBean.class);
//        try {
//            dependentBean.write("aaa");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testLookup() {
//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext("applicationContext.xml");
//        System.out.println("=======singleton sayHello======");
//        HelloApi helloApi1 = context.getBean("helloApi1", HelloApi.class);
//        helloApi1.sayHello();
//        helloApi1 = context.getBean("helloApi1", HelloApi.class);
//        helloApi1.sayHello();
//        System.out.println("=======prototype sayHello======");
//        HelloApi helloApi2 = context.getBean("helloApi2", HelloApi.class);
//        helloApi2.sayHello();
//        helloApi2 = context.getBean("helloApi2", HelloApi.class);
//        helloApi2.sayHello();
    }
}
