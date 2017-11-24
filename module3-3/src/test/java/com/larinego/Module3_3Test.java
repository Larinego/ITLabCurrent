package com.larinego;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/BeansAnnotations.xml"})
public class Module3_3Test {

    static Logger log;

    @Resource(name="namedFile")
    public File defaultFile;

    @BeforeClass
    public static void before(){
        log = Logger.getAnonymousLogger();
    }

    @Test
    public void byName(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Book book = context.getBean("bookByName", Book.class);

        log.info(book.toString());
    }

    @Test
    public void byType(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Book book = context.getBean("bookByType", Book.class);

        log.info(book.toString());
    }

    @Test
    public void byConstructor(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Office office = context.getBean("officeByConstrucor", Office.class);

        log.info(office.toString());
    }

    @Test
    public void autowiredOptionalQualifying(){
        ApplicationContext context = new ClassPathXmlApplicationContext("BeansAnnotations.xml");

        Building building = context.getBean("building", Building.class);

        log.info(building.toString());
    }

    @Test
    public void customQualifying(){
        ApplicationContext context = new ClassPathXmlApplicationContext("BeansAnnotations.xml");

        Flat flat = context.getBean("flat", Flat.class);

        log.info(flat.toString());
    }

    @Test
    public void resource(){
        ApplicationContext context = new ClassPathXmlApplicationContext("BeansAnnotations.ml");


        Flat flat = context.getBean("flat", Flat.class);

        log.info(flat.toString());
    }
}
