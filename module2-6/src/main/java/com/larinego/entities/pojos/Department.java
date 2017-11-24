package com.larinego.entities.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEPARTMENT")
@EqualsAndHashCode(callSuper = false)
public class Department{

    @Id @GeneratedValue
    @Column(name = "DEPARTMENT_ID")
    private Integer departmentId;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private List<Employee> employees = new ArrayList<>(0);
}
