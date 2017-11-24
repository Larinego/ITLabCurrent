package com.larinego;

import com.larinego.entities.pojos.Department;
import com.larinego.entities.pojos.Employee;
import com.larinego.entities.pojos.Person;
import com.larinego.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Module2_6Test {


    EntityManager entityManager;
    Department departmentManagement;
    Department departmentProduction;
    Employee alex_larin;
    Employee petr_ivanov;
    Employee ivan_petrov;
    Employee roy_johns;
    Employee li_sin;
    Person person;


    @Before
    public void before(){
        entityManager = HibernateUtil.getEntityManager();
        departmentManagement = new Department(null, "Management", new ArrayList<>());
        departmentProduction = new Department(null, "Production", new ArrayList<>());

        alex_larin = new Employee(null, "Alex Larin", 27, "",departmentManagement);
        petr_ivanov = new Employee(null, "Petr Ivanov", 21,"",departmentManagement);
        ivan_petrov = new Employee(null, "Ivan Petrov", 27,"",departmentProduction);
        roy_johns = new Employee(null, "Roy Johns", 45,"",departmentProduction);
        li_sin = new Employee(null, "Li Sin", 27,"",departmentProduction);

        person = new Person();
        person.setName("Alex Larin");
        person.setBiography("Was barn but not yet died");

        departmentManagement.getEmployees().add(alex_larin);
        departmentManagement.getEmployees().add(petr_ivanov);

        departmentProduction.getEmployees().add(ivan_petrov);
        departmentProduction.getEmployees().add(roy_johns);
        departmentProduction.getEmployees().add(li_sin);

        entityManager.getTransaction().begin();
        entityManager.persist(departmentManagement);
        entityManager.persist(departmentProduction);
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        entityManager.clear();

    }

    @Test
    public void selectNameTest() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("select d from Department d");
        System.out.println(query.list());
    }

    @Test
    public void selectIdTest() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Employee e where e.id in(:ids)");
        List<Integer> employeeIds = Arrays.asList(ivan_petrov.getEmployeeId(), petr_ivanov.getEmployeeId());
        query.ord"ids", employeeIds);
        System.out.println(query.list());
    }

    @Test
    public void selectSeveralTest() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Employee e where e.id in(:ids) and e.department=:department");
        List<Integer> employeeIds = Arrays.asList(ivan_petrov.getEmployeeId(), petr_ivanov.getEmployeeId());
        query.setParameter("ids", employeeIds);
        query.setParameter("department", departmentManagement);
        System.out.println(query.list());
    }

    @Test
    public void selectGroupByTest() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("select count(e.age), e.age from Employee e group by e.age");
        List resultList = query.getResultList();

        System.out.println(query.getResultList());
        for (Object o : resultList) {
            System.out.println("Count of " + ((Object[])o)[1] + " age employees is " + ((Object[])o)[0]);
        }

    }

    @Test
    public void selectDifferentTypeTest() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("select e.name, p.biography from Employee e join Person p on e.name = p.name ");
        List resultList = query.getResultList();

        System.out.println(query.getResultList());
        for (Object o : resultList) {
            System.out.println("Employee " + ((Object[])o)[1] + " biography is " + ((Object[])o)[1]);
        }

    }

    @Test
    public void updateTest() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("select e.name, e.employeeId, p.biography from Employee e join Person p on e.name = p.name ");
        List resultList = query.getResultList();

        System.out.println(query.getResultList());
        for (Object o : resultList) {
            entityManager.getTransaction().begin();
            javax.persistence.Query updatedQuery = entityManager.createQuery("update Employee e set e.about = :about where e.employeeId = :employeeId");
            updatedQuery.setParameter("about", (String)((Object[])o)[2]);
            updatedQuery.setParameter("employeeId", (Integer)((Object[])o)[1]);
            updatedQuery.executeUpdate();
            entityManager.getTransaction().commit();
        }

        System.out.println(entityManager.find(Employee.class, alex_larin.getEmployeeId()));

    }

    @Test
    public void selectGroupMaxMinMediateByTest() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("select distinct (e.age), min(e.age), avg(e.age), sum(e.age) from Employee e");
        List resultList = query.getResultList();

        System.out.println(query.getResultList());
        for (Object o : resultList) {
            System.out.println("Max of " + ((Object[])o)[0] + " age employees");
            System.out.println("Min of " + ((Object[])o)[1] + " age employees");
            System.out.println("Avg of " + ((Object[])o)[2] + " age employees");
            System.out.println("Sum of " + ((Object[])o)[3] + " age employees");
        }

    }

    @Test
    public void selectPaginationTest() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Employee");

        query.setFirstResult(0);
        query.setMaxResults(2);
        System.out.println(query.list());

        query.setFirstResult(2);
        query.setMaxResults(2);
        System.out.println(query.list());

        query.setFirstResult(4);
        query.setMaxResults(2);
        System.out.println(query.list());

        query.setFirstResult(0);
        query.setMaxResults(3);
        System.out.println(query.list());

        query.setFirstResult(3);
        query.setMaxResults(3);
        System.out.println(query.list());

        query.setFirstResult(0);
        query.setMaxResults(5);
        System.out.println(query.list());
    }



}
