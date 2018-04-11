package com.lijq.mybatis.v2;

import com.lijq.mybatis.dao.User;
import com.lijq.mybatis.v2.annotation.Select;

import java.io.Serializable;

/**
 * @author Lijq
 * @date 2018/4/2 11:12
 * @description
 */
public interface UserMapper {

    @Select(value = "select * from t_user where id = ?")
    User selectByPrimaryKey(Serializable id);

}