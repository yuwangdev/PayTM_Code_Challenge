package com.example.demo.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yuwang on 5/26/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDaoTest {

    @Test
    public void testGetInstance() throws Exception {
        Assert.assertNotNull(DaoFactory.getUserDao());

    }

    @Test
    public void testSave() throws Exception {
        UserDao userDao = DaoFactory.getUserDao();
        userDao.save("tom", "tompass");
        Assert.assertTrue(userDao.findUserByUserName("tom").getPassword().equalsIgnoreCase("tompass"));
    }

    @Test
    public void testCheckCredential() throws Exception {
        UserDao userDao = DaoFactory.getUserDao();
        userDao.save("James", "jamespass");
        Assert.assertFalse(userDao.findUserByUserName("James").getPassword().equalsIgnoreCase("tompass"));

    }


    @Test
    public void testFindAllUser() throws Exception {
        UserDao userDao = DaoFactory.getUserDao();
        userDao.save("tom", "tompass");
        userDao.save("James", "jamespass");
        Assert.assertEquals(userDao.countTotalUsers(), 2);
    }

    @Test
    public void testUpdatePassword() throws Exception {
        UserDao userDao = DaoFactory.getUserDao();
        userDao.save("tom", "tompass");
        userDao.updatePassword("tom", "tompass", "tomp4");
        Assert.assertEquals(userDao.findUserByUserName("tom").getPassword(), "tomp4");

    }


    @Test
    public void testDeleteUserByUsername() throws Exception {
        UserDao userDao = DaoFactory.getUserDao();
        userDao.save("tom", "tompass");
        userDao.save("James", "jamespass");
        Assert.assertEquals(userDao.countTotalUsers(), 2);
        userDao.deleteUserByUsername("tom");
        Assert.assertEquals(userDao.countTotalUsers(), 1);
    }
}