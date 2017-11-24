package com.larinego.entities.loader;

import com.larinego.entities.pojos.User;
import com.larinego.entities.util.HibernateUtil;

import javax.persistence.EntityManager;

public class UserLoader {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(27);
        user.setName("Alex");
        user.setSurname("Larin");
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        HibernateUtil.close();
    }
}
