package com.example.demo.model;

import lombok.Data;

/**
 * Created by yuwang on 5/26/17.
 */
@Data
public class UserUpdateRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}
