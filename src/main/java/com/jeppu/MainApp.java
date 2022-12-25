package com.jeppu;

import com.jeppu.domain.Person;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.List;

/**
 * Hibernate JPA Demo
 *
 */
public class MainApp {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");
    public static void main( String[] args ) {
        //createQuery();
        createNamedQuery();
        entityManagerFactory.close();
    }

    private static void createNamedQuery(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        TypedQuery<Person> allPersonTypedQuery = entityManager.createNamedQuery("Person.everyone", Person.class);
        allPersonTypedQuery.getResultList().forEach(System.out::println);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void createQuery(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        //1. Query
        //Query query = entityManager.createQuery("from Person");

        //2. TypedQuery
        TypedQuery<Person> query = entityManager.createQuery("from Person", Person.class);
        List<Person> personList = query.getResultList();
        System.out.println(personList);

        entityManager.getTransaction().commit();

        entityManager.close();
    }


}
