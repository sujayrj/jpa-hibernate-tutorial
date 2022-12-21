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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first", length = 250, nullable = false)
    private String firstName;

    @Column(name = "last", unique = true, nullable = false, length = 250)
    private String lastName;
}
