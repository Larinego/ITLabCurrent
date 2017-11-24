package com.larinego;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Data
public class Dog implements InitializingBean, DisposableBean {

    String name;
    int age;

    @Override
    public void destroy() throws Exception {
        System.out.println("Destroy dog");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing dog");
    }


}
