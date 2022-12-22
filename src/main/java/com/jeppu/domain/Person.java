package com.jeppu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @OneToMany

    //creates a seperate table with 2 columns - person_id and address_id
    //@JoinTable(name = "person_address", joinColumns = {@JoinColumn(name = "person_id")}, inverseJoinColumns = {@JoinColumn(name = "address_id")})

    //creates a column in Address table with name - person_id
    @JoinColumn(name = "person_id")
    private List<Address> addressList;
}
