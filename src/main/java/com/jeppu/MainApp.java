package com.jeppu;

import com.jeppu.domain.Person;

import javax.persistence.*;

/**
 * Hibernate JPA Demo
 *
 */
public class MainApp {
    public static void main( String[] args ) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        //Create an instance of Person and persist to DB
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("Sujay");
        person.setLastName("Jeppu");

        transaction.begin();
        entityManager.persist(person);

        //JPQL - refers Entity name and not Table
        System.out.println("*** JPQL ***");
        Query jpqlQuery = entityManager.createQuery("from people");
        jpqlQuery.getResultList().forEach(System.out::println);

        //Native query - refers Table name
        System.out.println("\n*** Native Query ***");
        Query nativeQuery = entityManager.createNativeQuery("select * from person", Person.class);
        nativeQuery.getResultList().forEach(System.out::println);

        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
