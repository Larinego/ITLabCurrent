package com.larinego;


import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Module3_5Test {

    @Test
    public void ArgInterceptTest(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MainClassInterrceptor());
        proxyFactory.setTarget(new MainClass());
        MainClass mainClassProxy = (MainClass)proxyFactory.getProxy();

        mainClassProxy.proxyBasedTargetMethod("First arg");
        mainClassProxy.proxyBasedTargetMethod("disable");
    }

    @Test
    public void SpringAopTestBeforeAfter(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
new XmlConfig
        MainClass mainClass = context.getBean("mainClass", MainClass.class);

        mainClass.aopSpringTargetMethodBefore();

    }

    @Test
    public void SpringAopTestAround(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        MainClass mainClass = context.getBean("mainClass", MainClass.class);

        mainClass.aopSpringTargetMethodAround();

    }


    @Test(expected = RuntimeException.class)
    public void SpringAopExceptionTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        MainClass mainClass = context.getBean("mainClass", MainClass.class);

        mainClass.aopSpringTargetMethodWithException();

    }

    @Test
    public void SpringAopTestParam(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        MainClass mainClass = context.getBean("mainClass", MainClass.class);

        mainClass.aopSpringTargetMethod("Alex", "Larin");

    }

    @Test
    public void SpringAopTestBeforeAfterAnnotated(){
        ApplicationContext context = new ClassPathXmlApplicationContext("AnnotatedBeans.xml");

        MainClass mainClass = context.getBean("mainClass", MainClass.class);

        mainClass.aopSpringTargetMethodBefore();

    }

    @Test
    public void SpringAopTestAroundAnnotated(){
        ApplicationContext context = new ClassPathXmlApplicationContext("AnnotatedBeans.xml");

        MainClass mainClass = context.getBean("mainClass", MainClass.class);

        mainClass.aopSpringTargetMethodAround();

    }


    @Test(expected = RuntimeException.class)
    public void SpringAopExceptionTestAnnotated(){
        ApplicationContext context = new ClassPathXmlApplicationContext("AnnotatedBeans.xml");

        MainClass mainClass = context.getBean("mainClass", MainClass.class);

        mainClass.aopSpringTargetMethodWithException();

    }

    @Test
    public void SpringAopTestParamAnnotated(){
        ApplicationContext context = new ClassPathXmlApplicationContext("AnnotatedBeans.xml");

        MainClass mainClass = context.getBean("mainClass", MainClass.class);

        mainClass.aopSpringTargetMethod("Alex", "Larin");

    }


}
