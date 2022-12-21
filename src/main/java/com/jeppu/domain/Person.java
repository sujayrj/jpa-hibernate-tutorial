package com.jeppu.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity(name = "people")    //Entity name - referred when creating JPQL
@Table(name="person")       //Actual SQL table name
public class Person {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
}
