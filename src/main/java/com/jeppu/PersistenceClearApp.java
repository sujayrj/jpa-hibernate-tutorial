package com.jeppu;

import com.jeppu.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Persistence Clear demo
 * When entityManager.clear() is called - all the managed objects are moved to detached state
 * Advisible to flush() any changes before clearing the entityManager (persistence context)
 *
 */
public class PersistenceClearApp {
    public static void main( String[] args ) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        //Create an instance of Person and persist to DB
        Person person = new Person();
        person.setFirstName("Sujay");
        person.setLastName("Jeppu");

        transaction.begin();
        System.out.println("1----------------------");
        entityManager.persist(person);                      //Hibernate: insert into person (firstName, lastName) values (?, ?)
        System.out.println("2----------------------");
        System.out.println(entityManager.contains(person)); //true
        System.out.println("3----------------------");
        entityManager.clear();
        System.out.println(entityManager.contains(person)); //false
        System.out.println("4----------------------");
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
