package com.jeppu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;     //by default stores Date+Time. Use @Temporal to restrict either Date/Time

    //With LocalDate, you don't have to use Temporal annotation. Use LocalDateTime if you need time as well.
    @Column(name = "anniversary_date")
    private LocalDate anniversaryDate;
}
