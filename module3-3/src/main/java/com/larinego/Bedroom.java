package com.larinego;

import lombok.Data;

@Data
@BedroomAnnotation
public class Bedroom implements Room {

    private String name;

    public Bedroom(){}
}
