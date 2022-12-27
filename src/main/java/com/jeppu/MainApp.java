package com.jeppu;

import com.jeppu.domain.Account;
import com.jeppu.domain.CheckingAccount;
import com.jeppu.domain.SavingsAccount;

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

        transaction.begin();

        Account checkingAccount = new CheckingAccount(100.0, 20.0);
        entityManager.persist(checkingAccount);

        Account savingsAccount = new SavingsAccount(2000.0, 5.0);
        entityManager.persist(savingsAccount);

        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
