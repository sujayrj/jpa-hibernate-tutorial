package com.jeppu;

import com.jeppu.domain.Car;
import com.jeppu.domain.Person;

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

        //Create an instance of Person and persist to DB
        Person person1 = new Person();
        person1.setFirstName("Sujay");
        person1.setLastName("Jeppu");

        Person person2 = new Person();
        person2.setFirstName("Amara");
        person2.setLastName("Raja");

        Car car1 = new Car();
        car1.setColor("Blue");
        car1.setModel("Benz");
        //car1.setPersonList(Arrays.asList(person1, person2));

        Car car2 = new Car();
        car2.setColor("White");
        car2.setModel("Zen");
        //car2.setPersonList(Arrays.asList(person1));

        person1.setCarList(Arrays.asList(car1, car2));
        person2.setCarList(Arrays.asList(car1));

        transaction.begin();
        entityManager.persist(person1);
        entityManager.persist(person2);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
