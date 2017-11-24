package com.larinego;

import lombok.Data;

@Data
@BathroomAnnotation
public class Bathroom implements Room{

    private String name;

    public Bathroom(){}

}
