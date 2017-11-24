package com.larinego;

import com.larinego.entities.impl.UserDaoImpl;
import com.larinego.entities.pojos.User;
import com.larinego.entities.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.junit.*;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;

public class UserTest {

    User user;
    EntityManager entityManager;
    UserDaoImpl userDaoImpl;

    @Before
    public void before(){

        entityManager = HibernateUtil.getEntityManager();

        user = new User();
        user.setAge(27);
        user.setName("Alex");
        user.setSurname("Larin");

        userDaoImpl = new UserDaoImpl();


    }


    public void Merge(Serializable id){

        entityManager.clear();
        entityManager = HibernateUtil.getEntityManager();
        userDaoImpl = new UserDaoImpl();
        user = userDaoImpl.get(id);
    }

    @Test
    public void testUser(){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        User userFromDb = entityManager.find(User.class, user.getId());
        entityManager.getTransaction().commit();
        entityManager.close();
        Assert.assertEquals(user, userFromDb);
    }

    @Test
    public void testSave() {

        userDaoImpl.save(user);

        entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        User userFromDb = entityManager.find(User.class, this.user.getId());
        entityManager.getTransaction().commit();
        entityManager.close();

        Assert.assertEquals(this.user, userFromDb);
    }

    @Test
    public void testGet() {

        userDaoImpl.save(user);

        User userFromDb = userDaoImpl.get(this.user.getId());

        Assert.assertEquals(this.user, userFromDb);

    }

    @Test
    public void testUpdate() {
        userDaoImpl.save(user);
        user.setName("Matilda");
        userDaoImpl.update(user);
        User userFromDb = userDaoImpl.get(this.user.getId());
        Assert.assertEquals(user, userFromDb);
    }



    @Test
    public void testDelete() {
        userDaoImpl.save(user);
        userDaoImpl.delete(user.getId());
        User userFromDb = userDaoImpl.get(this.user.getId());
        Assert.assertEquals(null, userFromDb);
    }

    @Test
    @Ignore
    public void testSaveManualId() {
        user.setId(123);
        userDaoImpl.saveManualId(user);
        userDaoImpl.delete(user.getId());
        User userFromDb = userDaoImpl.get(this.user.getId());
        Assert.assertEquals(null, userFromDb);
    }

    @Test
    public void testFindGet(){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        User userFromDb = entityManager.find(User.class, user.getId());
        entityManager.getTransaction().commit();
        entityManager.close();
        Assert.assertEquals(user, userFromDb);
    }

    @Test
    public void testGetReferenceLoad(){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        User userFromDb = entityManager.getReference(User.class, user.getId());
        entityManager.getTransaction().commit();
        //entityManager.close();
        Assert.assertEquals(user, userFromDb);
        entityManager.close();

    }

    @AfterClass
    public static void after(){
        HibernateUtil.close();
    }
}

