package com.larinego;

import com.larinego.entities.pojos.*;
import com.larinego.entities.pojos.singleTable.Person;
import com.larinego.entities.pojos.singleTable.Student;
import com.larinego.entities.pojos.singleTable.Teacher;
import com.larinego.entities.pojos.tablePerClass.PersonTablePerClass;
import com.larinego.entities.pojos.tablePerClass.StudentTablePerClass;
import com.larinego.entities.pojos.tablePerClass.TeacherTablePerClass;
import com.larinego.entities.pojos.tableSubClass.PersonTableSubClass;
import com.larinego.entities.pojos.tableSubClass.StudentTableSubClass;
import com.larinego.entities.pojos.tableSubClass.TeacherTableSubClass;
import com.larinego.entities.util.HibernateUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class Module2_4Test {

    @Test
    public void mappedSuperclassTest() {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Department department = new Department();
        department.setName("management");

        Employee employee1 = new Employee();
        employee1.setName("Alex");
        employee1.setDepartment(department);


        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Employee employee2 = new Employee();
        employee2.setName("Bob");
        employee2.setDepartment(department);

        department.getEmployees().add(employee1);
        department.getEmployees().add(employee2);

        entityManager.persist(department);
        entityManager.getTransaction().commit();


        entityManager.getTransaction().begin();
        Employee employee1FromDb = entityManager.find(Employee.class, employee1.getEmployeeId());
        System.out.println("Created date " + employee1FromDb.getCreatedDate());
        System.out.println("First updated date " + employee1FromDb.getUpdatedDate());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        employee1FromDb.setName("Alexandr");
        entityManager.getTransaction().commit();


        employee1FromDb = entityManager.find(Employee.class, employee1.getEmployeeId());
        System.out.println("Created date " + employee1FromDb.getCreatedDate());
        System.out.println("Second updated date " + employee1FromDb.getUpdatedDate());
        entityManager.close();

    }

    @Test
    public void singleTableInheritanceTest() {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        Person person = new Person();
        person.setName("Alex");
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        entityManager.clear();

        person.setName("Alexandr");
        entityManager.getTransaction().begin();
        entityManager.merge(person);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        System.out.println("personFromDb " + entityManager.find(Person.class, person.getId()));
        entityManager.getTransaction().commit();
        entityManager.clear();


        Student student = new Student();
        student.setName("Student");
        student.setSpecialization("IT");

        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        Student studentFromDb = entityManager.find(Student.class, student.getId());
        entityManager.getTransaction().commit();
        entityManager.clear();

        System.out.println("studentFromDb" + studentFromDb);

        Teacher teacher = new Teacher();
        teacher.setName("Yuli");
        teacher.setSubject("Java");
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        System.out.println("teacherFromDb " + entityManager.find(Teacher.class, teacher.getId()));
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        Person personStudentFromDb = entityManager.find(Person.class, student.getId());
        entityManager.getTransaction().commit();
        entityManager.clear();

        System.out.println("personLikeStudentFromDb " + personStudentFromDb);

        List<Person> persons = new ArrayList<>();

        Student student1 = new Student();
        student1.setName("Student-person");
        student1.setSpecialization("Student-person specialization");

        Teacher teacher1 = new Teacher();
        teacher1.setName("Teacher-person");
        teacher1.setSubject("Teacher-person subject");

        persons.add(student1);
        persons.add(teacher1);

        entityManager.getTransaction().begin();
        for (Person p : persons) {
            entityManager.persist(p);
        }
        entityManager.getTransaction().commit();
        entityManager.clear();

        Student student1FromDb = entityManager.find(Student.class, student1.getId());
        System.out.println("studentAfterSaveLikePersonFromDb" + student1FromDb);

        entityManager.close();

    }

    @Test
    public void tablePerClassTableInheritanceTest() {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        PersonTablePerClass person = new PersonTablePerClass();
        person.setName("Alex");
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        entityManager.clear();

        person.setName("Alexandr");
        entityManager.getTransaction().begin();
        entityManager.merge(person);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        System.out.println("personFromDb " + entityManager.find(PersonTablePerClass.class, person.getId()));
        entityManager.getTransaction().commit();
        entityManager.clear();


        StudentTablePerClass student = new StudentTablePerClass();
        student.setName("Student");
        student.setSpecialization("IT");

        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        StudentTablePerClass studentFromDb = entityManager.find(StudentTablePerClass.class, student.getId());
        entityManager.getTransaction().commit();
        entityManager.clear();

        System.out.println("studentFromDb " + studentFromDb);

        TeacherTablePerClass teacher = new TeacherTablePerClass();
        teacher.setName("Yuli");
        teacher.setSubject("Java");
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        System.out.println("teacherFromDb " + entityManager.find(TeacherTablePerClass.class, teacher.getId()));
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        PersonTablePerClass personStudentFromDb = entityManager.find(PersonTablePerClass.class, student.getId());
        entityManager.getTransaction().commit();
        entityManager.clear();

        System.out.println("personLikeStudentFromDb " + personStudentFromDb);

        List<PersonTablePerClass> persons = new ArrayList<>();

        StudentTablePerClass student1 = new StudentTablePerClass();
        student1.setName("Student-person");
        student1.setSpecialization("Student-person specialization");

        TeacherTablePerClass teacher1 = new TeacherTablePerClass();
        teacher1.setName("Teacher-person");
        teacher1.setSubject("Teacher-person subject");

        persons.add(student1);
        persons.add(teacher1);

        entityManager.getTransaction().begin();
        for (PersonTablePerClass p : persons) {
            entityManager.persist(p);
        }
        entityManager.getTransaction().commit();
        entityManager.clear();

        StudentTablePerClass student1FromDb = entityManager.find(StudentTablePerClass.class, student1.getId());
        System.out.println("studentAfterSaveLikePersonFromDb " + student1FromDb);

        entityManager.close();

    }
    @Test
    public void tableSubClassTableInheritanceTest() {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        PersonTableSubClass person = new PersonTableSubClass();
        person.setName("Alex");
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        entityManager.clear();

        person.setName("Alexandr");
        entityManager.getTransaction().begin();
        entityManager.merge(person);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        System.out.println("personFromDb " + entityManager.find(PersonTableSubClass.class, person.getId()));
        entityManager.getTransaction().commit();
        entityManager.clear();


        StudentTableSubClass student = new StudentTableSubClass();
        student.setName("Student");
        student.setSpecialization("IT");

        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        StudentTableSubClass studentFromDb = entityManager.find(StudentTableSubClass.class, student.getId());
        entityManager.getTransaction().commit();
        entityManager.clear();

        System.out.println("studentFromDb " + studentFromDb);

        TeacherTableSubClass teacher = new TeacherTableSubClass();
        teacher.setName("Yuli");
        teacher.setSubject("Java");
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        System.out.println("teacherFromDb " + entityManager.find(TeacherTableSubClass.class, teacher.getId()));
        entityManager.getTransaction().commit();
        entityManager.clear();

        entityManager.getTransaction().begin();
        PersonTableSubClass personStudentFromDb = entityManager.find(PersonTableSubClass.class, student.getId());
        entityManager.getTransaction().commit();
        entityManager.clear();

        System.out.println("personLikeStudentFromDb " + personStudentFromDb);

        List<PersonTableSubClass> persons = new ArrayList<>();

        StudentTableSubClass student1 = new StudentTableSubClass();
        student1.setName("Student-person");
        student1.setSpecialization("Student-person specialization");

        TeacherTableSubClass teacher1 = new TeacherTableSubClass();
        teacher1.setName("Teacher-person");
        teacher1.setSubject("Teacher-person subject");

        persons.add(student1);
        persons.add(teacher1);

        entityManager.getTransaction().begin();
        for (PersonTableSubClass p : persons) {
            entityManager.persist(p);
        }
        entityManager.getTransaction().commit();
        entityManager.clear();

        StudentTableSubClass student1FromDb = entityManager.find(StudentTableSubClass.class, student1.getId());
        System.out.println("studentAfterSaveLikePersonFromDb " + student1FromDb);

        entityManager.close();

    }

}