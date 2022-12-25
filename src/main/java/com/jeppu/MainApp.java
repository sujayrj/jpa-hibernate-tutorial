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

        Address address = new Address("Mangalore club road", "575001");
        person.setAddress(address);
        address.setPerson(person);

        transaction.begin();

        //add Cascade in Person, so it performs cascade operations on Address
        //entityManager.persist(person);
        //entityManager.remove(person);

        //add Cascade in Address, so it performs cascade operations on Address
        entityManager.persist(address);
        entityManager.remove(address);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
