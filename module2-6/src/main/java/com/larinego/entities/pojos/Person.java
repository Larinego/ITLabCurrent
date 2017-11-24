package com.larinego.entities.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Person {

    @Id @GeneratedValue
    private Integer personId;

    private String name;

    private String biography;

}
