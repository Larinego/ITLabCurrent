package com.larinego.entities.pojos.tablePerClass;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
public class TeacherTablePerClass extends PersonTablePerClass{

    private String subject;

}
