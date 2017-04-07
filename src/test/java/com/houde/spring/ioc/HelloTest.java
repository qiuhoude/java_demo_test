package com.houde.spring.ioc;

import com.houde.spring.ioc.HelloApi;
import com.houde.spring.ioc.impl.CollectionTestBean;
import com.houde.spring.ioc.impl.HelloApiDecorator;
import com.houde.spring.ioc.impl.IdRefTestBean;
import com.houde.spring.ioc.impl.ListTestBean;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HelloTest {

    private ApplicationContext context;
    private ApplicationContext parentContext;

    @Before
    public void before() {
        //1、读取配置文件实例化一个IoC容器
        parentContext = new ClassPathXmlApplicationContext("parentContext.xml");
        context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"}, parentContext);
    }

    @After
    public void after() {
        System.out.println("结束");
    }

    @Test
    public void testHelloWorld() {
        //2、从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
//        HelloApi helloApi = context.getBean("hello", HelloApi.class);
        HelloApi helloApi = (HelloApi) context.getBean("hello_name");
        HelloApi helloApi2 = (HelloApi) context.getBean("hello2_name");
        HelloApi helloApi3 = (HelloApi) context.getBean("hello_name");
        //3、执行业务逻辑
        helloApi.sayHello();
        helloApi2.sayHello();

        //helloApi 和 helloApi3 hashCode 相等 说明默认是单例的
        System.out.println("helloApi " + helloApi.hashCode());
        System.out.println("helloApi2 " + helloApi2.hashCode());
        System.out.println("helloApi3 " + helloApi3.hashCode());
    }


    @Test
    public void testInstantiatingBeanByConstructor() {
        //使用构造器
        HelloApi bean1 = context.getBean("bean1", HelloApi.class);
        bean1.sayHello();
        HelloApi bean2 = context.getBean("bean2", HelloApi.class);
        bean2.sayHello();
    }

    @Test
    public void testInstantiatingBeanByStaticFactory() {
        //使用静态工厂方法
        HelloApi bean3 = context.getBean("bean3", HelloApi.class);
        bean3.sayHello();
    }


    @Test
    public void testInstantiatingBeanByInstanceFactory() {
        //使用实例工厂方法
        HelloApi bean4 = context.getBean("bean4", HelloApi.class);
        HelloApi bean4_1 = context.getBean("bean4_1", HelloApi.class);
        bean4.sayHello();
        bean4_1.sayHello();
    }

    @Test
    public void testConstructorDependencyInjectTest() {
        //获取根据参数索引依赖注入的Bean
        HelloApi byIndex = context.getBean("byIndex", HelloApi.class);
        byIndex.sayHello();
        //获取根据参数类型依赖注入的Bean
        HelloApi byType = context.getBean("byType", HelloApi.class);
        byType.sayHello();
        //获取根据参数名字依赖注入的Bean
        HelloApi byName = context.getBean("byName", HelloApi.class);
        byName.sayHello();
    }

    @Test
    public void testSetterDependencyInject() {
        HelloApi bean = context.getBean("bean_setter", HelloApi.class);
        bean.sayHello();
    }

    @Test
    public void testIdIoc() {
        IdRefTestBean testBean1 = context.getBean("idrefBean1", IdRefTestBean.class);
        System.out.println(testBean1.getId());
        IdRefTestBean testBean2 = context.getBean("idrefBean2", IdRefTestBean.class);
        System.out.println(testBean2.getId());
    }


    @Test
    public void testListInject() {
        ListTestBean listBean = context.getBean("listBean", ListTestBean.class);
        System.out.println(listBean.getValues());
        Assert.assertEquals(3, listBean.getValues().size());

        CollectionTestBean collectionTestBean = context.getBean("setBean", CollectionTestBean.class);
        System.out.println(collectionTestBean.getValues());
        System.out.println(collectionTestBean.getMaps());

    }

    @Test
    public void testLocalAndparentBeanInject() {

        HelloApiDecorator bean1 = context.getBean("HelloApiDecorator1", HelloApiDecorator.class);
        bean1.getHelloApi().sayHello();//该Bean引用local bean
        HelloApiDecorator bean2 = context.getBean("HelloApiDecorator2", HelloApiDecorator.class);
        bean2.getHelloApi().sayHello();//该Bean引用parent bean
    }
}
