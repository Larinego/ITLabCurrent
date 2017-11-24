package com.larinego;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component("person")
public class Person {

    @Value("Artem")
    private String name;

    @Value("#{address}")
    private Address address;

    private Address secondAddress;

    private int age;

    void init(){
        age = 27;
    }

}
