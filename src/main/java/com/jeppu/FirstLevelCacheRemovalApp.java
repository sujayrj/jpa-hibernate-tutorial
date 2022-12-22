package com.jeppu;

import com.jeppu.domain.Book;
import com.jeppu.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FirstLevelCacheRemovalApp {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");
    private static EntityManager entityManager = null;

    public static void main(String[] args) {
        //removePersonEntity();
        removeBookEntity();
        closeEntityManagerFactory();
    }

    private static void removeBookEntity() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Book book = new Book();
        book.setIsbn("ABC");
        book.setBookName("Just React");
        book.setAuthorName("Sujay");
        book.setPublisher("ABC publishing house");
        System.out.println("1----------------------");
        entityManager.persist(book);
        System.out.println("2----------------------");
        book.setPublisher("Shroff publishing");
        System.out.println("3----------------------");
        entityManager.remove(book);
        System.out.println("4----------------------");
        entityManager.getTransaction().commit();            //Hibernate: insert into Book (authorName, bookName, publisher, isbn) values (?, ?, ?, ?)
                                                            //Hibernate: delete from Book where isbn=?
        System.out.println("5----------------------");
        entityManager.close();
    }

    //inserts for Surrogate key are fired right away during persist(). any updates performed on the Entity prior to deletes are ignored.
    private static void removePersonEntity() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Person person = new Person();
        person.setFirstName("Ashok");
        person.setLastName("Kumar");
        System.out.println("1----------------------");
        entityManager.persist(person);                      //Hibernate: insert into person (firstName, lastName) values (?, ?)
        System.out.println("2----------------------");
        person.setLastName("Raja");
        System.out.println("3----------------------");
        entityManager.remove(person);
        System.out.println("4----------------------");
        entityManager.getTransaction().commit();            //Hibernate: delete from person where id=?
        System.out.println("5----------------------");
        entityManager.close();
    }

    private static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
}
