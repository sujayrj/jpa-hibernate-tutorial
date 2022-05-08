package com.jeppu.repository;

import com.jeppu.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StudentRepository {
    private final EntityManagerFactory factory;
    public StudentRepository(String persistenceProvider){
        factory = Persistence.createEntityManagerFactory(persistenceProvider);
    }

    public Long add(Student student) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(student);
        manager.getTransaction().commit();
        manager.close();
        return student.getId();
    }

    public Student find(Long id) {
        EntityManager manager = factory.createEntityManager();
        Student student = manager.find(Student.class, id);
        manager.close();
        return student;
    }

    public Student update(Student student) {
        EntityManager manager = factory.createEntityManager();
        Student studentToUpdate = manager.find(Student.class, student.getId());
        manager.getTransaction().begin();
        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());
        manager.getTransaction().commit();
        manager.close();
        return studentToUpdate;
    }

    public void delete(Long id) {
        EntityManager manager = factory.createEntityManager();
        Student student = manager.find(Student.class, id);
        manager.getTransaction().begin();
        manager.remove(student);
        manager.getTransaction().commit();
        manager.close();
    }

    public void close() {
        this.factory.close();
    }
}
