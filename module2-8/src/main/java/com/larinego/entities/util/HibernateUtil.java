package com.larinego.entities.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.security.PrivateKey;

public class HibernateUtil {

    private static final EntityManagerFactory emFactory;
    private static final ThreadLocal<EntityManager> entityManagerThreadLocal;

    static {
        emFactory = Persistence.createEntityManagerFactory("com.larinego");
        entityManagerThreadLocal = new ThreadLocal<EntityManager>();
    }

    public static EntityManager getEntityManager(){
        EntityManager entityManager = entityManagerThreadLocal.get();
        if (entityManager == null){
            entityManager = emFactory.createEntityManager();
            entityManagerThreadLocal.set(entityManager);
        }

        return entityManager;
    }
    public static void closeEntityManagerFactory() {
        emFactory.close();
    }

    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public static void rollback() {
        getEntityManager().getTransaction().rollback();
    }

    public static void commit() {
        getEntityManager().getTransaction().commit();
    }

}
