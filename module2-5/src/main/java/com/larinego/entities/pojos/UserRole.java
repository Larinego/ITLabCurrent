package com.larinego.entities.pojos;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude={"users"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER_ROLE")
public class UserRole {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "USER_ROLE_ID", unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "NAME")
    private String userRoleName;

    @Getter @Setter
    @OneToMany(mappedBy = "userRole")
    @Access(AccessType.PROPERTY)
    private Set<User> users = new HashSet<>(0);
}
