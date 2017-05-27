package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by yuwang on 5/24/17.
 */


public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}

