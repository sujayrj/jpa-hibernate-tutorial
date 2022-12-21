package com.jeppu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="person")
public class Person {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)           AUTO ID GENERATION - For MySQL, its SEQUENCE
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)       SEQUENCE ID GENERATION
    //@GeneratedValue(strategy = GenerationType.TABLE)          TABLE ID GENERATION
    @GeneratedValue(strategy = GenerationType.IDENTITY)       //IDENTITY ID GENERATION (DB MANAGED COLUMN)
    private Long id;            //Surrogate Key
    private String firstName;
    private String lastName;
}
