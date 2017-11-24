package com.larinego;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {NoXmlXonfiguration.class})
public class Module3_4NoXmlTest {

    static Logger log;

    @BeforeClass
    public static void before(){
        log = Logger.getAnonymousLogger();
    }

    @Test
    public void noXxmlConfig(){
        //ApplicationContext context = new AnnotationConfigApplicationContext("com.larinego");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(NoXmlXonfiguration.class);
        context.refresh();
        context.start();

        Person person = context.getBean("person", Person.class);

        log.info(person.toString());
    }

    @Test
    public void noXxmlConfigBean(){

        ApplicationContext context = new AnnotationConfigApplicationContext("com.larinego");

        Person person = context.getBean("personBean", Person.class);

        log.info(person.toString());
    }

    @Test(expected = org.springframework.beans.factory.NoSuchBeanDefinitionException.class)
    public void excludeService(){

        ApplicationContext context = new AnnotationConfigApplicationContext("com.larinego");

        NotComponent notComponent = context.getBean("notComponent", NotComponent.class);

        log.info(notComponent.toString());
    }
}
