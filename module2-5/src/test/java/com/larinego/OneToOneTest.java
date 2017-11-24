package com.larinego;

import com.larinego.entities.impl.UserDaoImpl;
import com.larinego.entities.pojos.User;
import com.larinego.entities.pojos.UserDetail;
import com.larinego.entities.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

public class OneToOneTest {

    User user;
    UserDetail userDetail;
    EntityManager entityManager;
    UserDaoImpl userDaoImpl;

    @Before
    public void before(){
        entityManager = HibernateUtil.getEntityManager();
    }

    @Test
    public void persistTest(){

        user = new User();
        user.setAge(27);
        user.setName("Alex");
        user.setSurname("Larin");


        userDetail = new UserDetail();
        userDetail.setCountry("Belarus");
        userDetail.setCity("Minsk");
        userDetail.setStreet("Kalinovskogo st.");

        user.setUserDetail(userDetail);
        userDetail.setUser(user);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
