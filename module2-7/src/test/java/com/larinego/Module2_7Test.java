package com.larinego;


import com.larinego.entities.impl.UserDaoImpl;

import com.larinego.entities.pojos.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class Module2_7Test{


    static UserDaoImpl userDao;

    @BeforeClass
    static public void beforeClass(){
        userDao = UserDaoImpl.getInstance();
    }

    @Before
    public void before(){
        User user1 = new User(null, 22, 2500.00,"Alex", "Larin");
        User user2 = new User(null, 25, 2600.00,"Petr", "Malcev");
        User user3 = new User(null, 26, 400.00,"Ivan", "Marceev");
        User user4 = new User(null, 22, 4000.00,"Ivan", "Shitsko");
        User user5 = new User(null, 18, 1800.00,"Oleg", "Romanov");
        User user6 = new User(null, 55, 2500.00,"Roman", "Malcev");
        User user7 = new User(null, null, null,"Ivan", "Grigoriev");
        userDao.save(user1);
        userDao.save(user2);
        userDao.save(user3);
        userDao.save(user4);
        userDao.save(user5);
        userDao.save(user6);
        userDao.save(user7);

    }


    @Test
    public void getAllUsersTest(){
        System.out.println(userDao.getAllUsers());
    }

    @Test
    public void getUsersNameLikeAndAgeNotNullTest(){
        System.out.println(userDao.getUsersNameLike("Ivan"));
    }

    @Test
    public void getUsersAgeBetweenTest(){
        System.out.println(userDao.getUsersAgeBetween(22,28));
    }

    @Test
    public void getSortedUsersTest(){
        System.out.println(userDao.getSortedUsers(true, "age"));
        System.out.println(userDao.getSortedUsers(false, "age"));
        System.out.println(userDao.getSortedUsers(true, "salary"));
        System.out.println(userDao.getSortedUsers(false, "salary"));
    }

    @Test
    public void getCountOfUsersTest(){
        System.out.println(userDao.getCountOfUsers());

    }

    @Test
    public void getFieldMaxValueOfUsersTest(){
        //System.out.println(userDao.getFieldMaxValueOfUsers("age"));
        System.out.println(userDao.getFieldMaxValueOfUsers("salary"));

    }

    @Test
    public void getFieldAvgValueOfUsersTest(){
        System.out.println(userDao.getFieldAvgValueOfUsers("age"));
        System.out.println(userDao.getFieldAvgValueOfUsers("salary"));

    }

    @Test
    public void getFieldSumValueOfUsersTest(){
        //System.out.println(userDao.getFieldSumValueOfUsers("age"));
        System.out.println(userDao.getFieldSumValueOfUsers("salary"));

    }

    @Test
    public void getAllUsersPaginatedTest(){
        System.out.println(userDao.getAllUsersPaginated(0,2));
        System.out.println(userDao.getAllUsersPaginated(2,2));
        System.out.println(userDao.getAllUsersPaginated(4,2));
        System.out.println(userDao.getAllUsersPaginated(6,2));

        System.out.println(userDao.getAllUsersPaginated(0,3));
        System.out.println(userDao.getAllUsersPaginated(3,3));
        System.out.println(userDao.getAllUsersPaginated(6,3));

        System.out.println(userDao.getAllUsersPaginated(0,5));
        System.out.println(userDao.getAllUsersPaginated(5,5));

    }



}
