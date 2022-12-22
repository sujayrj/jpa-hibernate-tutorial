package com.jeppu;

import com.jeppu.domain.Book;
import com.jeppu.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PushChangesToDBApp {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");
    private static EntityManager entityManager = null;

    public static void main(String[] args) {
        pushChangesToDb();
        entityManagerFactory.close();
    }

    //changes are pushed to DB during - commit, query() or flush()
    private static void pushChangesToDb() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Book book = new Book();
        book.setBookName("Spring Security");
        book.setIsbn("ABC");
        book.setAuthorName("Sujay");
        book.setPublisher("RedLabel");
        System.out.println("1------------------------");
        entityManager.persist(book);
        System.out.println("2------------------------");
        //1. commit()
        //entityManager.getTransaction().commit();        //Hibernate: insert into Book (authorName, bookName, publisher, isbn) values (?, ?, ?, ?)

        //2.Using query.getResultList()
        //TypedQuery<Book> query = entityManager.createQuery("from Book", Book.class);
        //query.getResultList();                         //Hibernate: insert into Book (authorName, bookName, publisher, isbn) values (?, ?, ?, ?)

        //3. Using flush
        entityManager.flush();                              //Hibernate: insert into Book (authorName, bookName, publisher, isbn) values (?, ?, ?, ?)
        System.out.println("3------------------------");
        System.out.println("4------------------------");
        entityManager.close();
    }
}
