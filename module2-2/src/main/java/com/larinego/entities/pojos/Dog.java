package com.larinego.entities.pojos;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Dog {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private int age;

    @Column
    private  String name;

    @Column
    private  String surname;

}

