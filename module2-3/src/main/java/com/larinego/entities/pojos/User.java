package com.larinego.entities.pojos;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "identity")
    private Integer id;

    @Column
    private int age;

    @Column
    private  String name;

    @Column
    private  String surname;

}

