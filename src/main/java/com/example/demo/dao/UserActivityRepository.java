package com.example.demo.dao;

import com.example.demo.model.UserActivity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by yuwang on 5/26/17.
 */
public interface UserActivityRepository extends CrudRepository<UserActivity, Long> {

    List<UserActivity> findByUsername(String username);

}
