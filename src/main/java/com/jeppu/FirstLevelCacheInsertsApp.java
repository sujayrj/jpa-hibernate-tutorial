package com.jeppu;

import com.jeppu.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hibernate JPA Demo
 */
public class FirstLevelCacheInsertsApp {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");
    private static EntityManager entityManager = null;

    public static void main(String[] args) {
        persistIdentityEntity();

        closeEntityManagerFactory();
    }

    //1. persist() when Entity has ID generation strategy = IDENTITY.
    // inserts are fired right away when persist() is invoked and not during commit()
    private static void persistIdentityEntity() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Person person = new Person();
        person.setFirstName("Sujay");
        person.setLastName("Jeppu");
        System.out.println("1------------------");
        entityManager.persist(person);                  //Hibernate: insert into person (firstName, lastName) values (?, ?)
        System.out.println("2------------------");
        entityManager.getTransaction().commit();
        System.out.println("3------------------");
        entityManager.close();
    }

    //1. persist() when Entity has ID generation strategy = TABLE. (MySQL)
    // inserts are NOT fired right away during persist(), but held in cache and fired during commit()
    //Note: a new table called hibernate_sequences is created which has 2 columns - sequence_name and next_val
    private static void persistTableIdGenerationEntity() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Person person = new Person();
        person.setFirstName("Sujay");
        person.setLastName("Jeppu");
        System.out.println("1------------------");
        entityManager.persist(person);                  //Hibernate: select sequence_next_hi_value from hibernate_sequences where sequence_name = 'person' for update
                                                        //Hibernate: insert into hibernate_sequences(sequence_name, sequence_next_hi_value) values('person', ?)
                                                        //Hibernate: update hibernate_sequences set sequence_next_hi_value = ? where sequence_next_hi_value = ? and sequence_name = 'person'
        System.out.println("2------------------");
        entityManager.getTransaction().commit();        //Hibernate: insert into person (firstName, lastName, id) values (?, ?, ?)
        System.out.println("3------------------");
        entityManager.close();
    }


    private static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
}
