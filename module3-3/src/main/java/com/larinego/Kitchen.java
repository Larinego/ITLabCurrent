package com.larinego;

import lombok.Data;

@Data
@KitchenAnnotation
public class Kitchen implements Room{

    private String name;

    public Kitchen(){
    }
}
