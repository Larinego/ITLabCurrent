package com.larinego;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(basePackages = "com.larinego",
excludeFilters = {@ComponentScan.Filter(
        value = MyAddressAnnotation.class,
        type = FilterType.ANNOTATION
)})
public class NoXmlXonfiguration {

    @Bean(value = "personBean",
    autowire = Autowire.BY_TYPE,initMethod = "init")
    public Person person(){
        Person p = new Person();
        return p;
    }

}
