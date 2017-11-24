package com.larinego;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SenderTest {

    @Before
    public void before(){


    }

    @Test
    public void mySenderTest(){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        SenderService senderService = context.getBean("senderService", SenderService.class);

        Message message = new Message();
        message.setMessage("Hello");

        senderService.send(message);

    }


}
