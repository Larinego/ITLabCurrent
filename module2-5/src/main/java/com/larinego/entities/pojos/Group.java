package com.larinego.entities.pojos;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude={"users"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="GROUP_TABLE")
public class Group {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Long id;

    @Getter @Setter
    @Column(name = "NAME")
    private  String name;

    @Getter @Setter
    @ManyToMany(mappedBy = "groups")
    private Set<User> users = new HashSet<>(0);
}
