package com.larinego.entities.pojos;

import com.larinego.entities.pojos.Department;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
@ToString(exclude = "department")
@EqualsAndHashCode(exclude = "department", callSuper = false)
public class Employee{

    @Id @GeneratedValue
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "ABOUT")
    private String about;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;


}
