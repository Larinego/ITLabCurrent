package com.larinego.entities.impl;

import com.larinego.entities.DAO;
import com.larinego.entities.pojos.User;
import com.larinego.entities.util.HibernateUtil;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.sql.SQLException;

public class UserDaoImpl implements DAO<User> {

    @Override
    public boolean save(User user) {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return false;
    }

    @Override    
    public User get(Serializable id) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        User user = null;
        try {
            entityManager.getTransaction().begin();
            user = entityManager.find(User.class, id);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return user;
        
        
        
    }

    @Override
    public boolean update(User user) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            user = entityManager.merge(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return false;

    }

    @Override
    public boolean delete(Serializable id) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        User user = null;
        try {
            entityManager.getTransaction().begin();
            user = entityManager.find(User.class, id);
            if (user != null) {
                entityManager.remove(user);
            }
            entityManager.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return false;
    }

    public boolean saveManualId(User user) {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return false;
    }


}
