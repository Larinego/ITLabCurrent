package com.larinego;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Person implements Executable{

    private static Person person = new Person();

    private String name;

    public static Person getInstance(){
        return person;
    }

    @Override
    public void perfom() {
        System.out.println("Hello " + name );
    }
}
