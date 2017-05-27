package com.example.demo.model;

import javax.persistence.*;

/**
 * Created by yuwang on 5/24/17.
 */
@Entity(name = "User")
@Table(name = "User")
public class User {

    private String username;
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
