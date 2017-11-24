package com.larinego.entities.pojos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
@ToString(exclude = "department")
@EqualsAndHashCode(exclude = "department", callSuper = false)
public class Employee extends DateCreateUpdate{

    @Id @GeneratedValue
    @Column(name = "EMPLOYEE_ID")
    private int employeeId;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
}
