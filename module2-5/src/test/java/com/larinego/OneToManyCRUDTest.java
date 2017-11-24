package com.larinego;

import com.larinego.entities.impl.UserDaoImpl;
import com.larinego.entities.pojos.User;
import com.larinego.entities.pojos.UserDetail;
import com.larinego.entities.pojos.UserRole;
import com.larinego.entities.util.HibernateUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

public class OneToManyCRUDTest {

    User user;
    UserDetail userDetail;
    EntityManager entityManager;
    UserDaoImpl userDaoImpl;

    @Before
    public void before(){

        UserRole userRole= new UserRole();
        userRole.setUserRoleName("Manager");


        userDaoImpl = UserDaoImpl.getInstance();

        user = new User();
        user.setAge(27);
        user.setName("Alex");
        user.setSurname("Larin");
        user.setUserRole(userRole);

    }

    @Test
    public void testSave() {

        userDaoImpl.save(user);

        entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        User userFromDb = entityManager.find(User.class, user.getId());
        entityManager.getTransaction().commit();
        entityManager.close();

        Assert.assertEquals(user, userFromDb);
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
        user.setName("Ivan");
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
}
