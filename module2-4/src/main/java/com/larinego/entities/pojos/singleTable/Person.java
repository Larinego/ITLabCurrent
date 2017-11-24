package com.larinego.entities.pojos.singleTable;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PERSON_TYPE",
        discriminatorType = DiscriminatorType.CHAR)
//@DiscriminatorFormula("case when COMPANY is not null then 'E' else 'S' end")
//@DiscriminatorOptions(force = false, insert = true)
@DiscriminatorValue("P")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
}
