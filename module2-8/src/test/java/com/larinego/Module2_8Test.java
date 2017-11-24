package com.larinego;

import com.larinego.entities.pojos.*;

import com.larinego.entities.util.HibernateUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;


public class Module2_8Test {



    @BeforeClass
    static public void beforeClass(){

    }

    @Before
    public void before(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CatLockVersion catLockVersion = new CatLockVersion(null, "Barsik", "Veronika", null);
        CatEasyVersion catEasyVersion = new CatEasyVersion(null, "Barsik", "Veronika", null);
        CatEasy catEasy = new CatEasy(null, "Barsik", "Veronika");
        CatLockAll  catLockAll = new CatLockAll(null, "Barsik", "Veronika");
        CatLockDirty catLockDirty = new CatLockDirty(null, "Barsik", "Veronika");
        entityManager.getTransaction().begin();
        entityManager.persist(catLockVersion);
        entityManager.persist(catEasyVersion);
        entityManager.persist(catEasy);
        entityManager.persist(catLockDirty);
        entityManager.getTransaction().commit();


    }

    @Test
    public void whithoutOptimisticVersionLockTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        CatEasy catLockVersion = entityManager.find(CatEasy.class, 1);
        catLockVersion.setOwner("Ilona");
        new Thread(new Runnable() {
            @Override
            public void run() {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                CatEasy catLockVersion = entityManager1.find(CatEasy.class, 1);
                catLockVersion.setOwner("Abram");
                entityManager1.getTransaction().commit();
                System.out.println("saved");
            }
        }).start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        entityManager.getTransaction().commit();

        System.out.println(entityManager.find(CatEasy.class, 1));
    }

    @Test(expected = javax.persistence.RollbackException.class)
    public void optimisticVersionLockTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        CatLockVersion catLockVersion = entityManager.find(CatLockVersion.class, 1);
        catLockVersion.setOwner("Ilona");
        new Thread(new Runnable() {
            @Override
            public void run() {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                CatLockVersion catLockVersion = entityManager1.find(CatLockVersion.class, 1);
                catLockVersion.setOwner("Abram");
                entityManager1.getTransaction().commit();
                System.out.println("saved");
            }
        }).start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        entityManager.getTransaction().commit();


    }

    @Test(expected = javax.persistence.RollbackException.class)
    public void optimisticAllLockTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        CatLockAll catLockVersion = entityManager.find(CatLockAll.class, 1);
        catLockVersion.setOwner("Ilona");
        new Thread(new Runnable() {
            @Override
            public void run() {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                CatLockAll catLockVersion = entityManager1.find(CatLockAll.class, 1);
                catLockVersion.setOwner("Abram");
                entityManager1.getTransaction().commit();
                System.out.println("saved");
            }
        }).start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        entityManager.getTransaction().commit();


    }

    @Test(expected = javax.persistence.RollbackException.class)
    public void optimisticDirtyLockTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        CatLockDirty catLockVersion = entityManager.find(CatLockDirty.class, 1);
        catLockVersion.setOwner("Ilona");
        new Thread(new Runnable() {
            @Override
            public void run() {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                CatLockDirty catLockVersion = entityManager1.find(CatLockDirty.class, 1);
                catLockVersion.setOwner("Abram");
                entityManager1.getTransaction().commit();
                System.out.println("saved");
            }
        }).start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        entityManager.getTransaction().commit();

    }


    @Test(expected = javax.persistence.RollbackException.class)
    public void optimisticVersionForceIncrementLockTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        CatEasyVersion catEasyVersion = entityManager.find(CatEasyVersion.class, 1);
        catEasyVersion.setOwner("Ilona");
        new Thread(new Runnable() {
            @Override
            public void run() {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();

                //CatLockVersionOptimistic catLockVersionOptimistic1 = entityManager1.find(CatLockVersionOptimistic.class, 1,LockModeType.OPTIMISTIC_FORCE_INCREMENT);

                Query query = entityManager1.createQuery("from CatEasyVersion");
                query.setLockMode(LockModeType.OPTIMISTIC_FORCE_INCREMENT);
                System.out.println(query.getResultList());

                /*CatLockVersionOptimistic catLockVersionOptimistic1 = entityManager1.find(CatLockVersionOptimistic.class, 1);
                entityManager1.lock(catLockVersionOptimistic1, LockModeType.OPTIMISTIC_FORCE_INCREMENT);*/

                System.out.println("edit some information about cat");
                entityManager1.getTransaction().commit();
                System.out.println("saved");
            }
        }).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(catEasyVersion.getOwner());
        entityManager.getTransaction().commit();


    }

    @Test
    public void pessimisticVersionReadWriteLockTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();

        CatEasyVersion catEasyVersion = entityManager.find(CatEasyVersion.class, 1, LockModeType.PESSIMISTIC_WRITE);

         /*Query query = entityManager.createQuery("from CatEasyVersion");
                query.setLockMode(LockModeType.PESSIMISTIC_READ);
                System.out.println(query.getResultList());*/

        /*CatEasyVersion catEasyVersion1 = entityManager1.find(CatEasyVersion.class, 1);
                entityManager1.lock(catEasyVersion1, LockModeType.PESSIMISTIC_READ);*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();

                CatEasyVersion catEasyVersion1 = entityManager1.find(CatEasyVersion.class, 1, LockModeType.PESSIMISTIC_READ);
                catEasyVersion1.setName("Bob");

                System.out.println("our object " + catEasyVersion1);
                entityManager1.getTransaction().commit();
                System.out.println("after commit");
            }
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("waiting end");
        catEasyVersion.setName("Oleg");
        entityManager.getTransaction().commit();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }







}
