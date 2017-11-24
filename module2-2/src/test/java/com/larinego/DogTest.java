package com.larinego;

import com.larinego.entities.pojos.Dog;
import com.larinego.entities.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;

public class DogTest {

    @Test
    public void testDog() {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        Dog dog = new Dog();

        dog.setAge(27);
        dog.setName("Alex");
        dog.setSurname("Larin");

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(dog);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Dog userFromDb = entityManager.find(Dog.class, dog.getId());
        entityManager.getTransaction().commit();
        entityManager.close();
        Assert.assertEquals(dog, userFromDb);
    }
}