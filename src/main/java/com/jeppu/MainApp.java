package com.jeppu;

import com.jeppu.domain.Book;
import com.jeppu.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Hibernate JPA Demo
 */
public class MainApp {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");

    public static void main(String[] args) {
        //persistEntityWithSurrogateKey();
        //mergeEntityWithSurrogateKey();
        persistEntityWithNaturalKey();
        closeEntityManagerFactory();
    }

    private static void persistVsmerge(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    //On trying to merge entity with natrual key with same ISBN will update the first record
    private static void mergeEntityWithNaturalKey(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Book book1 = new Book();
        book1.setBookName("JPA with Hiberate");
        book1.setPublisher("Tukaram Publishing house");
        book1.setIsbn("111-222");

        Book book2 = new Book();
        book2.setBookName("Spring Boot");
        book2.setPublisher("BPC Publishing House");
        book2.setIsbn("111-222");

        entityManager.getTransaction().begin();
        entityManager.merge(book1);
        entityManager.merge(book2);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    //On trying to persist entity with natural key with same ISBN will throw an Exception - javax.persistence.EntityExistsException:
                //A different object with the same identifier value was already associated with the session
    private static void persistEntityWithNaturalKey(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Book book1 = new Book();
        book1.setBookName("JPA with Hiberate");
        book1.setPublisher("Tukaram Publishing house");
        book1.setIsbn("111-222");

        Book book2 = new Book();
        book2.setBookName("Spring Boot");
        book2.setPublisher("BPC Publishing House");
        book2.setIsbn("111-222");

        entityManager.getTransaction().begin();
        entityManager.persist(book1);
        entityManager.persist(book2);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void persistEntityWithSurrogateKey() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Person p1 = new Person();
        p1.setFirstName("Sujay");
        p1.setLastName("Jeppu");

        entityManager.persist(p1);
        transaction.commit();

        entityManager.close();
    }

    public static void mergeEntityWithSurrogateKey() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        Person p1 = entityManager.find(Person.class, 1L);
        entityManager.detach(p1);
        p1.setLastName("Ram");
        p1 = entityManager.merge(p1);
        p1.setLastName("Rama");
        transaction.commit();

        entityManager.close();
    }

    private static void closeEntityManagerFactory(){
        entityManagerFactory.close();
    }
}
