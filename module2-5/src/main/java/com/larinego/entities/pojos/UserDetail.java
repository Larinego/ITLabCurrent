package com.larinego.entities.pojos;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@EqualsAndHashCode(exclude={"user"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER_DETAIL")
public class UserDetail {

    @Getter @Setter
    @Id
    @GenericGenerator(name = "one-to-one", strategy = "foreign", parameters = @Parameter(name = "property", value ="user"))
    @GeneratedValue(generator = "one-to-one")
    @Access(AccessType.PROPERTY)
    @Column(name = "USER_DETAIL_ID")
    private long id;

    @Getter @Setter
    @Column(name = "COUNTRY")
    private  String country;

    @Getter @Setter
    @Column(name = "CITY")
    private  String city;

    @Getter @Setter
    @Column(name = "STREET")
    private  String street;

    @Getter @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    @Access(AccessType.PROPERTY)
    private User user;



}
