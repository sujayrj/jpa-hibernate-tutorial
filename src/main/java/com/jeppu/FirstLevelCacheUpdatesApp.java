package com.jeppu;

import com.jeppu.domain.Book;
import com.jeppu.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FirstLevelCacheUpdatesApp {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");
    private static EntityManager entityManager = null;

    public static void main(String[] args) {
        //updatePersonEntity();
        updateBookEntity();
        closeEntityManagerFactory();
    }

    //for natural key, although inserts are during commit, an update is also issued
    private static void updateBookEntity(){
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Book book1 = new Book();
        book1.setIsbn("ABC");
        book1.setAuthorName("Sujay Jeppu");
        book1.setBookName("Spring Boot");
        book1.setPublisher("ABC publishing house");
        System.out.println("1--------------------");
        entityManager.persist(book1);
        System.out.println("2--------------------");
        book1.setPublisher("Book Shroff publishing house");
        System.out.println("3--------------------");
        book1.setBookName("Spring Boot with Security");
        System.out.println("4--------------------");
        entityManager.getTransaction().commit();        //Hibernate: insert into Book (authorName, bookName, publisher, isbn) values (?, ?, ?, ?)
                                                        //Hibernate: update Book set authorName=?, bookName=?, publisher=? where isbn=?
        System.out.println("5--------------------");
        entityManager.close();
    }

    //any changes made to a managed Entity (Surrogate key) will be held in cache and updated during commit.
    private static void updatePersonEntity(){
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = new Person();
        p1.setFirstName("Sujay");
        p1.setLastName("Jeppu");

        System.out.println("1--------------------");
        entityManager.persist(p1);                      //Hibernate: insert into person (firstName, lastName) values (?, ?)
        System.out.println("2--------------------");
        p1.setLastName("Ram");
        System.out.println("3--------------------");
        p1.setFirstName("Mr.Sujay");
        System.out.println("4--------------------");
        entityManager.getTransaction().commit();        //Hibernate: update person set firstName=?, lastName=? where id=?
        System.out.println("5--------------------");
        entityManager.close();
    }

    private static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
}
