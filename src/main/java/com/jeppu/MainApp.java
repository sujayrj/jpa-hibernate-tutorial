package com.jeppu;

import com.jeppu.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Date;

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
        person.setBirthDate(new Date());
        person.setAnniversaryDate(LocalDate.of(2012, 10, 9));

        transaction.begin();
        entityManager.persist(person);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
