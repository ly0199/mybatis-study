package com.lijq.mybatis.dao;

import lombok.Data;

/**
 * @author Lijq
 * @date 2018/4/2 11:13
 * @description
 */
@Data
public class User {

    private int id;
    private String username;
    private String password;
    private int age;
}
