package com.larinego;

import com.larinego.entities.pojos.Cat;
import com.larinego.entities.pojos.Dog;
import com.larinego.entities.pojos.User;
import com.larinego.entities.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;

public class Module2_3Test {

    @Test
    public void testId() {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();

        Dog dog = new Dog();

        dog.setAge(27);
        dog.setName("Alex");
        dog.setSurname("Larin");

        entityManager.persist(dog);

        System.out.println(dog.getId());

        dog = new Dog();

        dog.setAge(27);
        dog.setName("Alex");
        dog.setSurname("Larin");

        entityManager.persist(dog);

        System.out.println(dog.getId());

        Cat cat = new Cat();

        cat.setAge(27);
        cat.setName("Cat");
        cat.setSurname("Tom");


        entityManager.persist(cat);

        System.out.println(cat.getId());

        cat = new Cat();

        cat.setAge(27);
        cat.setName("Cat");
        cat.setSurname("Tom");
        cat.setNotSaved("Bum");

        entityManager.persist(cat);

        System.out.println(cat.getId());
        Integer catId = cat.getId();
        entityManager.clear();

       /* entityManager.getTransaction().begin();
        System.out.println(entityManager.find(Cat.class, catId));
        entityManager.getTransaction().commit();*/

        User user = new User();

        user.setAge(25);
        user.setName("Cat");
        user.setSurname("Tom");


        entityManager.persist(user);

        System.out.println(user.getId());

        user = new User();

        user.setAge(27);
        user.setName("Miu");
        user.setSurname("Tom");


        entityManager.persist(user);
        entityManager.getTransaction().commit();
        System.out.println(user.getId());

    }

    @Test
    public void testGetreferenceLoad() {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();

        Dog dog = new Dog();

        dog.setAge(27);
        dog.setName("Alex");
        dog.setSurname("Larin");

        entityManager.persist(dog);

        System.out.println(dog);
        entityManager.getTransaction().commit();
        entityManager.clear();


        entityManager.getTransaction().begin();
        Dog reference = entityManager.getReference(Dog.class, dog.getId());
        System.out.println("loaded");
        System.out.println(reference);
        entityManager.getTransaction().commit();

    }
}