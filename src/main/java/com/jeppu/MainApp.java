package com.jeppu;

import com.jeppu.domain.Person;
import com.jeppu.domain.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;

/**
 * Hibernate JPA Demo
 *
 */
public class MainApp {
    public static void main( String[] args ) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Address address1 = new Address();
        address1.setStreet("Mangalore club road");
        address1.setCity("Mangalore");
        address1.setState("Karnataka");

        Address address2 = new Address();
        address2.setStreet("Bangalore club road");
        address2.setCity("Bangalore");
        address2.setState("Karnataka");

        Person person = new Person();
        person.setFirstName("Sujay");
        person.setLastName("Jeppu");
        person.setAddressList(Arrays.asList(address1, address2));

        address1.setPerson(person);
        address2.setPerson(person);

        transaction.begin();
        entityManager.persist(person);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
