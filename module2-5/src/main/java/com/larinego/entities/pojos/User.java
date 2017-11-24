package com.larinego.entities.pojos;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude={"userDetail","userRole","groups"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER")
public class User {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "NAME")
    private  String name;

    @Getter @Setter
    @Column(name = "SURNAME")
    private  String surname;

    @Getter @Setter
    @Column(name = "AGE")
    private int age;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    @Getter @Setter
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    @Access(AccessType.PROPERTY)
    private UserDetail userDetail;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ROLE_ID")
    @Access(AccessType.PROPERTY)
    private UserRole userRole;

    @Getter @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "USER_GROUPS", joinColumns = {@JoinColumn(name ="USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "GROUP_ID")})
    @Access(AccessType.PROPERTY)
    private Set<Group> groups = new HashSet<>(0);

}

