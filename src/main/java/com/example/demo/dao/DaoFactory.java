package com.example.demo.dao;

/**
 * Created by yuwang on 5/26/17.
 */
public class DaoFactory {
    public static LcboDao getLcboDao() {
        return LcboDao.getInstance();
    }

    public static UserDao getUserDao() {
        return UserDao.getInstance();
    }

    public static UserActivityDao getUserActivityDao() {
        return UserActivityDao.getInstance();
    }
}
