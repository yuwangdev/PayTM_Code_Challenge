package com.example.demo.dao;

import com.example.demo.config.ApplicationContextProvider;
import com.example.demo.model.UserActivity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yuwang on 5/26/17.
 */
public class UserActivityDao {
    private static UserActivityDao intance;
    private UserActivityRepository userActivityRepository;

    private UserActivityDao() {
        this.userActivityRepository = ApplicationContextProvider.getBean(UserActivityRepository.class);
    }

    public static UserActivityDao getInstance() {
        if (intance == null) {
            intance = new UserActivityDao();
        }
        return intance;
    }

    public Boolean saveAccountRelatedInfo(String username, String event, Date date) {
        this.userActivityRepository.save(new UserActivity(username, event, date));
        return true;
    }

    public Boolean saveSearchRelatedInfo(String username, String event) {
        this.userActivityRepository.save(new UserActivity(username, event));
        return true;
    }

    public List<UserActivity> findByUsername(String username) {
        return this.userActivityRepository.findByUsername(username);

    }

    public String generateRandomNonExistedKey(String username) {
        List<UserActivity> searched = this.findByUsername(username);
        String result = getRandomWord(6);
        while (searched.stream().map(UserActivity::getSearchContent).collect(Collectors.toSet()).contains(result)) {
            result = getRandomWord(6);
        }

        return result;

    }

    private String getRandomWord(int length) {
        String r = "";
        for (int i = 0; i < length; i++) {
            r += (char) (Math.random() * 26 + 97);
        }
        return r;
    }
}
