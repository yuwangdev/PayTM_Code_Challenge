package com.example.demo.dao;


import com.example.demo.config.ApplicationContextProvider;
import com.example.demo.model.User;

import java.util.List;

/**
 * Created by yuwang on 5/24/17.
 */
public class UserDao {

    private static UserDao intance;
    private UserRepository userRepository;

    private UserDao() {
        this.userRepository = ApplicationContextProvider.getBean(UserRepository.class);
    }

    public static UserDao getInstance() {
        if (intance == null) {
            intance = new UserDao();
        }
        return intance;
    }

    public Boolean save(String username, String password) {
        if (this.isDuplicatedUsername(username)) return false;
        this.userRepository.save(new User(username, password));
        return true;
    }

    public boolean checkCredential(String username, String password) {
        User user = this.userRepository.findByUsername(username);
        if (user == null) return false;
        return user.getPassword().equals(password) && user.getUsername().equals(username);
    }

    public User findUserByUserName(String username) {
        return this.userRepository.findByUsername(username);
    }

    public List<User> findAllUser() {
        return (List) this.userRepository.findAll();
    }

    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        User user = this.userRepository.findByUsername(username);
        if (user == null) return false;
        if (!user.getPassword().equals(oldPassword)) return false;
        this.userRepository.delete(user.getId());
        this.userRepository.save(new User(username, newPassword));
        return true;

    }

    public long countTotalUsers() {
        return this.userRepository.count();
    }

    private Boolean isDuplicatedUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        return user != null;
    }

    public boolean deleteUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        if (user == null) return false;
        this.userRepository.delete(user.getId());
        return true;
    }


}
