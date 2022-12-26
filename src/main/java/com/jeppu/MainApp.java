package com.jeppu;

import com.jeppu.domain.Account;
import com.jeppu.domain.CheckingAccount;
import com.jeppu.domain.Person;
import com.jeppu.domain.SavingsAccount;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hibernate JPA Demo
 *
 */
public class MainApp {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");
    public static void main( String[] args ) {
        //createQuery();
        //createNamedQuery();
        //polymorphicQuery();
        //aliasQuery();
        //pagination();
        //orderBy();
        //whereClause();
        //queryParams();
        //singleResult();
        specialAttributeId();
        entityManagerFactory.close();
    }

    private static void specialAttributeId(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Person> personTypedQuery = entityManager.createQuery("from Person p where id = :id", Person.class);
        //The id attribute in Person Entity was changed to personId from id. But you can still the special attribute id (interally refers to personId in Person)
        personTypedQuery.setParameter("id", 1L);
        Person person = personTypedQuery.getSingleResult();
        System.out.println(person);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void singleResult(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Person> personTypedQuery = entityManager.createQuery("from Person p where p.firstName like :firstKey and p.lastName like :lastKey", Person.class);
        personTypedQuery.setParameter("firstKey", "%"+"Alice4"+"%");
        personTypedQuery.setParameter("lastKey", "%"+"Long"+"%");
        //with singleresult, atleast 1 result has to be returned else will throw NoResultException
        Person person = personTypedQuery.getSingleResult();
        System.out.println(person);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void queryParams(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        String firstName = "John";
        //bad practice to use string concatenation - vulernable to SQL injection
        //TypedQuery<Person> personTypedQuery = entityManager.createQuery("from Person p where p.firstName like '"+firstName+"'", Person.class);

        //1. Named Parameter - good practice to use query parameter
        TypedQuery<Person> personTypedQuery = entityManager.createQuery("from Person as p where p.firstName = :first and p.lastName=:last ", Person.class);
        personTypedQuery.setParameter("first", "John2");
        personTypedQuery.setParameter("last", "Doe2");

        //2. Position
        personTypedQuery = entityManager.createQuery("from Person as p where p.firstName = ?1 and p.lastName=?2", Person.class);
        personTypedQuery.setParameter(1, "John2");
        personTypedQuery.setParameter(2, "Doe2");

        personTypedQuery.getResultList().stream().map(person -> person.getFirstName()+" "+person.getLastName()).forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void whereClause(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Person> personTypedQuery = entityManager.createQuery("from Person p where p.firstName like 'Alice%' order by p.firstName asc", Person.class);
        personTypedQuery.getResultList().stream().map(person -> person.getFirstName()+" "+person.getLastName()).forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void orderBy(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Person> personTypedQuery = entityManager.createQuery("from Person p order by p.firstName desc", Person.class);
        personTypedQuery.getResultList().stream().map(person -> person.getFirstName()+" "+person.getLastName()).forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void pagination(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Person> allPersonQuery = entityManager.createQuery("from Person", Person.class);
        allPersonQuery.setFirstResult(0);
        allPersonQuery.setMaxResults(5);

        System.out.println(allPersonQuery.getResultList().stream().count());
        allPersonQuery.getResultList().stream().forEach(person -> System.out.println(person.getFirstName()));


        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void aliasQuery(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Person> personTypedQuery = entityManager.createQuery("from Person as p where p.id=1L", Person.class);
        personTypedQuery.getResultList().forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void polymorphicQuery(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Account checkingAccount = new CheckingAccount(100.00, 20.00);
        entityManager.persist(checkingAccount);

        Account savingsAccount = new SavingsAccount(2000.00, 200.00);
        entityManager.persist(savingsAccount);

        TypedQuery<Account> accountQuery = entityManager.createQuery("from Account", Account.class);
        accountQuery.getResultList().forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void createNamedQuery(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        TypedQuery<Person> allPersonTypedQuery = entityManager.createNamedQuery("Person.everyone", Person.class);
        allPersonTypedQuery.getResultList().forEach(System.out::println);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void createQuery(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        //1. Query
        //Query query = entityManager.createQuery("from Person");

        //2. TypedQuery
        TypedQuery<Person> query = entityManager.createQuery("from Person", Person.class);
        List<Person> personList = query.getResultList();
        System.out.println(personList);

        entityManager.getTransaction().commit();

        entityManager.close();
    }


}
