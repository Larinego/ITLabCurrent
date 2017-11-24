package com.larinego.entities.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Cat {

    @Id
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "increment")
    private Integer id;

    @Column
    private int age;

    @Column
    @Access(AccessType.FIELD)
    private  String name;

    @Column(updatable = false)
    @Access(AccessType.PROPERTY)
    private  String surname;

    @Column(insertable = false)
    private  String notSaved;

}
