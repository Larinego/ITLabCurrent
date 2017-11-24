package com.larinego.entities.impl;

import com.larinego.entities.DAO;
import com.larinego.entities.pojos.User;
import com.larinego.entities.util.HibernateUtil;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements DAO<User> {

    public static UserDaoImpl instance = new UserDaoImpl();

    public static UserDaoImpl getInstance() {
        return instance;
    }

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

    public List<User> getAllUsers() {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        criteriaQuery.from(User.class);
        //Root<User> userRoot = criteriaQuery.from(User.class);
        //criteriaQuery.select(userRoot);
        List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
        return users;
    }

    public List<User> getAllUsersPaginated(int startPosition, int maxResult) {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        criteriaQuery.from(User.class);
        //Root<User> userRoot = criteriaQuery.from(User.class);
        //criteriaQuery.select(userRoot);
        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(startPosition);
        typedQuery.setMaxResults(maxResult);
        typedQuery.getResultList();
        List<User> resultList = typedQuery.getResultList();
        return resultList;
    }


    public List<User> getUsersNameLike(String name) {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(userRoot.get("name"),name),
                criteriaBuilder.isNotNull(userRoot.get("age"))
        );
        criteriaQuery.select(userRoot).where(predicate);
        List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
        return users;
    }

    public List<User> getUsersAgeBetween(Integer low, Integer high) {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot).where(criteriaBuilder.between(userRoot.get("age"),low,high));
        List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
        return users;
    }

    public List<User> getSortedUsers(boolean desc, String sortedField) {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Order order;
        if(desc) {
            order = criteriaBuilder.desc(userRoot.get(sortedField));
        }
        else{
            order = criteriaBuilder.asc(userRoot.get(sortedField));
        }
        criteriaQuery.select(userRoot).orderBy(order);
        List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
        return users;
    }

    public Long getCountOfUsers() {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(criteriaBuilder.count(userRoot));
        Long count = entityManager.createQuery(criteriaQuery).getSingleResult();
        return count;
    }

    public Double getFieldMaxValueOfUsers(String field) {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(criteriaBuilder.max(userRoot.get(field)));
        Double count = entityManager.createQuery(criteriaQuery).getSingleResult();
        return count;
    }

    public Double getFieldAvgValueOfUsers(String field) {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(criteriaBuilder.avg(userRoot.get(field)));
        Double count = entityManager.createQuery(criteriaQuery).getSingleResult();
        return count;
    }

    public Double getFieldSumValueOfUsers(String field) {

        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(criteriaBuilder.sum(userRoot.get(field)));
        Double count = (Double)entityManager.createQuery(criteriaQuery).getSingleResult().doubleValue();
        return count;
    }


}
