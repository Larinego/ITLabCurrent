package com.larinego;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/Beans.xml"})
public class Module3_4Test {

    static Logger log;

    @BeforeClass
    public static void before(){
        log = Logger.getAnonymousLogger();
    }

    @Test
    public void annotationConfig(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Person person = context.getBean("person", Person.class);

        log.info(person.toString());
    }

    @Test(expected = org.springframework.beans.factory.NoSuchBeanDefinitionException.class)
    public void annotationConfigFilter(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        NotComponent notComponent = context.getBean("notComponent", NotComponent.class);

        log.info(notComponent.toString());
    }




}
