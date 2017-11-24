package com.larinego.entities.pojos.tableSubClass;

import com.larinego.entities.pojos.singleTable.Person;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
public class TeacherTableSubClass extends PersonTableSubClass {

    private String subject;

}
