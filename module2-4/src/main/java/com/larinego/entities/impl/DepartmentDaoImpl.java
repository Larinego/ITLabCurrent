package com.larinego.entities.impl;

import com.larinego.entities.DAO;
import com.larinego.entities.pojos.Department;
import com.larinego.entities.util.HibernateUtil;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class DepartmentDaoImpl  implements DAO<Department> {


    @Override
    public boolean save(Department department) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(department);
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
    public Department get(Serializable id) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Department department = null;
        try {
            entityManager.getTransaction().begin();
            department = entityManager.find(Department.class, id);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return department;
    }

    @Override
    public boolean update(Department department) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(department);
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
        Department department = null;
        try {
            entityManager.getTransaction().begin();
            department = entityManager.find(Department.class, id);
            if (department != null) {
                entityManager.remove(department);
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
}
