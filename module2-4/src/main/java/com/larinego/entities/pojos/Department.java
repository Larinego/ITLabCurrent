package com.larinego.entities.pojos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "DEPARTMENT")
@EqualsAndHashCode(callSuper = false)
public class Department extends DateCreateUpdate{

    @Id @GeneratedValue
    @Column(name = "DEPARTMENT_ID")
    private int departmentId;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private List<Employee> employees = new ArrayList<>(0);
}
