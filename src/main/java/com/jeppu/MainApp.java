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
        Person person = new Person();
        person.setFirstName("Sujay");
        person.setLastName("Jeppu");

        Car car1 = new Car();
        car1.setColor("Blue");
        car1.setModel("Benz");

        Car car2 = new Car();
        car2.setColor("White");
        car2.setModel("Zen");

        person.setCarList(Arrays.asList(car1, car2));

        transaction.begin();
        entityManager.persist(person);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
