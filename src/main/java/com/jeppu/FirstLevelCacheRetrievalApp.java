package com.jeppu;

import com.jeppu.domain.Book;
import com.jeppu.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hibernate JPA Demo
 */
public class FirstLevelCacheRetrievalApp {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");
    private static EntityManager entityManager = null;

    public static void main(String[] args) {
        //retrieveBook();
        retrievalPerson();
        closeEntityManagerFactory();
    }

    //retrieve a Person already in persistence context
    private static void retrievalPerson() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Person person = new Person();
        person.setFirstName("Sujay");
        person.setLastName("Jeppu");
        System.out.println("1---------------------");
        entityManager.persist(person);                      //Hibernate: insert into person (firstName, lastName) values (?, ?)
        System.out.println("2---------------------");
        Person personFromDB = entityManager.find(Person.class, 1L);
        System.out.println(personFromDB);                   //Person(id=1, firstName=Sujay, lastName=Jeppu)
        System.out.println("3---------------------");
        entityManager.getTransaction().commit();
        System.out.println("4---------------------");
        entityManager.close();
    }

    private static void retrieveBook(){
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Book book = new Book();
        book.setBookName("Hibernate with JPA");
        book.setAuthorName("Sujay Jeppu");
        book.setIsbn("ABCDEF123");
        System.out.println("1-------------------");
        entityManager.persist(book);
        System.out.println("2-------------------");
        Book bookFromDB = entityManager.find(Book.class, "ABCDEF123");
        System.out.println("3-------------------"+bookFromDB.getBookName());
        entityManager.getTransaction().commit();                                //Hibernate: insert into Book (authorName, bookName, publisher, isbn) values (?, ?, ?, ?)
        System.out.println("4-------------------");
        entityManager.close();
    }

    private static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
}
