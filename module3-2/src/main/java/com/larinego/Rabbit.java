package com.larinego;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Rabbit {

    String name;
    int age;
    public Set<Rabbit> children = new HashSet<>(0);

}

