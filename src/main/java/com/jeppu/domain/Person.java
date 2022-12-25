package com.jeppu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="person")
@NamedQueries({
    @NamedQuery(
            name="Person.everyone",
            query = "from Person"
    )
})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;
}
