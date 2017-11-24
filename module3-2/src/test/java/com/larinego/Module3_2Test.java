package com.larinego;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.logging.Logger;

public class Module3_2Test {

    static Logger log;

    @BeforeClass
    public static void before(){
        log = Logger.getAnonymousLogger();
    }

    @Test
    public void BeanFactoryTest(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Person person = context.getBean("person", Person.class);

        person.perfom();

    }

    @Test
    public void ConstructorArgBeanTest(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        ConstructorBean constructorBean = context.getBean("constructorBean", ConstructorBean.class);

        log.info(constructorBean.toString());

    }

    @Test
    public void InitializingDestroyBeanTest(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Dog dog = context.getBean("dog", Dog.class);

        log.info(dog.toString());

        ((ClassPathXmlApplicationContext)context).close();

    }

    @Test
    public void InitDestroyBeanTest(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Horse horse = context.getBean("horse", Horse.class);

        log.info(horse.toString());

        ((ClassPathXmlApplicationContext)context).close();

    }

    @Test
    public void SetBeanTest(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Rabbit rabbit = context.getBean("rabbit-mummy", Rabbit.class); //???Linked Hash Set

        log.info(rabbit.toString());

    }

    @Test
    public void MapBeanTest(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Basket basket = context.getBean("basket", Basket.class); //???Linked Hash map

        log.info(basket.toString());

    }

    @Test
    public void PropBeanTest(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        PropsBean propsBean = context.getBean("propsBean", PropsBean.class);

        log.info(propsBean.toString());

    }

    @Test
    public void NullBeanTest(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        NullValueBean nullValueBean = context.getBean("nullValueBean", NullValueBean.class);

        log.info(nullValueBean.toString());
    }

    @Test
    public void ExpBeanTest(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        ExpressionBean expressionBean = context.getBean("expBean", ExpressionBean.class);

        log.info(expressionBean.toString());
    }
}
