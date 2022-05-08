package com.jeppu.repository;

import com.jeppu.model.Student;
import org.junit.*;

import static org.junit.Assert.*;

public class StudentRepositoryTest {
    private StudentRepository studentRepository;

    @BeforeClass
    public static void beforeClass(){
        System.out.println("%%% beforeClass %%%");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("%%% afterClass %%%");
    }

    @Before
    public void setUp(){
        System.out.println("%%% setUp %%%");
        studentRepository = new StudentRepository("student_pu_test");
    }

    @After
    public void tearDown() {
        System.out.println("%%% tearDown %%%");
        studentRepository.close();
    }

    @Test
    public void add() {
        System.out.println("%%% add %%%");
        Student student = new Student();
        student.setFirstName("Sujay");
        student.setLastName("Jeppu");
        studentRepository.add(student);
        assertNotNull(student);
        assertEquals(1L, (long) student.getId());
    }

    @Test
    public void find() {
        System.out.println("%%% find %%%");
        Student student = new Student();
        student.setFirstName("Sujay");
        student.setLastName("Jeppu");
        studentRepository.add(student);
        student = studentRepository.find(student.getId());
        assertNotNull(student);
    }

    @Test
    public void update() {
        System.out.println("%%% update %%%");
        Student student = new Student();
        student.setFirstName("Akshay");
        student.setLastName("Kumar");
        studentRepository.add(student);
        student.setFirstName("Amar");
        Student updatedStudent = studentRepository.update(student);
        assertNotNull(updatedStudent);
        assertEquals("Amar", updatedStudent.getFirstName());
    }

    @Test
    public void delete() {
        System.out.println("%%% delete %%%");
        Student student = new Student();
        student.setFirstName("Ashok");
        student.setLastName("Singh");
        Long id = studentRepository.add(student);
        studentRepository.delete(id);
        assertNull(studentRepository.find(id));

    }
}