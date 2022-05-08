package com.jeppu;

import com.jeppu.model.Student;
import com.jeppu.repository.StudentRepository;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFirstName("Alan");
        student.setLastName("Walker");

        StudentRepository studentRepository = new StudentRepository("student_pu");
        Long addedStudentId = studentRepository.add(student);
        System.out.println("*** Added Student : " + student);

        student = studentRepository.find(student.getId());
        System.out.println("*** Found Student : "+student);

        student.setLastName("Green");
        student = studentRepository.update(student);
        System.out.println("*** Updated Student : "+student);

        studentRepository.delete(student.getId());
        System.out.println("*** Deleted Student : "+student);

    }
}
