package com.larinego;


import lombok.ToString;

@ToString
public class ConstructorBean {

    private String name;

    public ConstructorBean(String name){
        this.name = name;
    }

    public ConstructorBean(){
    }


}
