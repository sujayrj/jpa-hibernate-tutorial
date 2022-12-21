package com.jeppu.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Book {
    @Id
    private String isbn;    //Natural key
    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "publisher", nullable = false)
    private String publisher;
}
