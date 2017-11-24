package com.larinego.entities.impl;

import com.larinego.entities.DAO;
import com.larinego.entities.util.HibernateUtil;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class UserWhithoutAutoIdDaoImpl<T> implements DAO<T> {

    @Override
    public boolean save(T t) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
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
    public T get(Serializable id) {
        return null;
    }

    @Override
    public boolean update(T t) {
        return false;
    }

    @Override
    public boolean delete(Serializable id) {
        return false;
    }
}
