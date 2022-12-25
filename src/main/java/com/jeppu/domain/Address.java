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
    private String zipCode;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Person person;

    public Address(String street, String zipCode){
        this.zipCode = zipCode;
        this.street = street;
    }

}
