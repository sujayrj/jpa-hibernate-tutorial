package com.jeppu;

import com.jeppu.domain.Address;
import com.jeppu.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Hibernate JPA Demo
 */
public class ManyToOneApp {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Person person = new Person();
        person.setFirstName("Sujay");
        person.setLastName("Jeppu");

        Address address1 = new Address();
        address1.setStreet("Mangalore club road");
        address1.setPerson(person);
        address1.setZipCode("575001");

        Address address2 = new Address();
        address2.setStreet("Bangalore club road");
        address2.setPerson(person);
        address2.setZipCode("560037");

        entityManager.persist(person);
        entityManager.persist(address1);
        entityManager.persist(address2);

        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
