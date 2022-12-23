package com.jeppu;

import com.jeppu.domain.Address;
import com.jeppu.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
        person.setFirstName("Sujay");
        person.setLastName("Jeppu");

        Address address = new Address();
        address.setState("Karnataka");
        address.setStreet("Mangalore club road");
        address.setCity("Mangalore");

        person.setAddress(address);

        transaction.begin();
        entityManager.persist(address);
        entityManager.persist(person);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
