package com.example.demo.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

/**
 * Created by yuwang on 5/26/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserActivityDaoTest {

    @Before
    public void prepare() {
        UserDao userDao = DaoFactory.getUserDao();
        userDao.save("tom", "tompass");


    }

    @Test
    public void testGetInstance() throws Exception {
        Assert.assertNotNull(DaoFactory.getUserActivityDao());

    }

    @Test
    public void testSaveAccountRelatedInfo() throws Exception {
        UserActivityDao userActivityDao = DaoFactory.getUserActivityDao();
        userActivityDao.saveAccountRelatedInfo("tom", "create acount", Calendar.getInstance().getTime());
        Assert.assertEquals(userActivityDao.findByUsername("tom").size(), 1);
        Assert.assertEquals(userActivityDao.findByUsername("tom").get(0).getEvent(), "create acount");


    }

    @Test
    public void testSaveSearchRelatedInfo() throws Exception {
        UserActivityDao userActivityDao = DaoFactory.getUserActivityDao();
        userActivityDao.saveSearchRelatedInfo("tom", "RedWine");
        Assert.assertEquals(userActivityDao.findByUsername("tom").size(), 1);
        Assert.assertEquals(userActivityDao.findByUsername("tom").get(0).getSearchContent(), "RedWine");


    }


    @Test
    public void testGenerateRandomNonExistedKey() throws Exception {
        UserActivityDao userActivityDao = DaoFactory.getUserActivityDao();
        Assert.assertEquals(userActivityDao.generateRandomNonExistedKey("tom").length(), 6);
        System.out.println(userActivityDao.generateRandomNonExistedKey("tom"));

    }
}