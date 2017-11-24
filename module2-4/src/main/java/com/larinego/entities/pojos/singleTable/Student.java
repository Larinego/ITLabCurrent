package com.larinego.entities.pojos.singleTable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("S")
@ToString(callSuper = true)
public class Student extends Person{

    private String specialization;

}
