package com.larinego;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Data
public class Horse {

    String name;
    int age;

    public void destroy() throws Exception {
        System.out.println("Destroy horse");
    }

    public void init() throws Exception {
        System.out.println("Init horse");
    }


}
