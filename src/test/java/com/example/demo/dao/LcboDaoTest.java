package com.example.demo.dao;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yuwang on 5/26/17.
 */
public class LcboDaoTest {

    @Test
    public void testGetInstance() throws Exception {
        Assert.assertNotNull(DaoFactory.getLcboDao());

    }

    @Test
    public void testGetLcboDataResponse() throws Exception {
        LcboDao lcboDao = DaoFactory.getLcboDao();
        Assert.assertTrue(lcboDao.getLcboDataResponse("Canadian").size() > 0);
    }
}