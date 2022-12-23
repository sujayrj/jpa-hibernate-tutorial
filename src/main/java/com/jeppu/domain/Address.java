package com.jeppu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String state;
    @ManyToOne
    @JoinTable(name = "person_address",
            joinColumns = {@JoinColumn(name = "address_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")}
    )
    private Person person;
}