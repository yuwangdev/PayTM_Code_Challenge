package com.example.demo.service;

import com.example.demo.dao.DaoFactory;
import com.example.demo.dao.LcboDao;
import com.example.demo.dao.UserActivityDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

/**
 * Created by yuwang on 5/24/17.
 */

@RestController
public class RestfulService {

    private LcboDao lcboDao = DaoFactory.getLcboDao();
    private UserDao userDao = DaoFactory.getUserDao();
    private UserActivityDao userActivityDao = DaoFactory.getUserActivityDao();

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String hello() {
        return "Hello PayTM!";
    }


    @RequestMapping(value = "/search/{username}/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LcboData> searchLCBO(@PathVariable String key, @PathVariable String username) {
        this.userActivityDao.saveSearchRelatedInfo(username, key);
        return this.lcboDao.getLcboDataResponse(key);
    }

    @RequestMapping(value = "/random/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<LcboData> randomSearchearchLCBO(@PathVariable String username) {
        String key = this.userActivityDao.generateRandomNonExistedKey(username);
        this.userActivityDao.saveSearchRelatedInfo(username, key);
        return this.lcboDao.getLcboDataResponse(key);
    }


    @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean loginCheckCredential(@RequestBody UserRequest loginRequestModel) {
        return this.userDao.checkCredential(loginRequestModel.getUsername(), loginRequestModel.getPassword());
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean createUser(@RequestBody UserRequest userRequest) {
        Boolean status = this.userDao.save(userRequest.getUsername(), userRequest.getPassword());
        if (status) {
            this.userActivityDao.saveAccountRelatedInfo(userRequest.getUsername(), "create acount", Calendar.getInstance().getTime());
        }
        return status;
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean updateUser(@RequestBody UserUpdateRequest userRequest) {
        Boolean status = this.userDao.updatePassword(userRequest.getUsername(), userRequest.getOldPassword(), userRequest.getNewPassword());
        if (status) {
            this.userActivityDao.saveAccountRelatedInfo(userRequest.getUsername(), "update acount", Calendar.getInstance().getTime());
        }
        return status;
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User findByUsername(@PathVariable String username) {
        return this.userDao.findUserByUserName(username);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> findAllUser() {
        return this.userDao.findAllUser();
    }

    @RequestMapping(value = "/user/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public long countUser() {
        return this.userDao.countTotalUsers();
    }

    @RequestMapping(value = "/user/delete/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean delete(@PathVariable String username) {
        this.userActivityDao.saveAccountRelatedInfo(username, "delete acount", Calendar.getInstance().getTime());
        return this.userDao.deleteUserByUsername(username);
    }

    @RequestMapping(value = "/activity/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserActivity> getActivityByUserName(@PathVariable String username) {
        return this.userActivityDao.findByUsername(username);

    }
}
