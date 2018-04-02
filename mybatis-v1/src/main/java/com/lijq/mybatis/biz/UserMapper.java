package com.lijq.mybatis.biz;

import java.io.Serializable;

/**
 * @author Lijq
 * @date 2018/4/2 11:12
 * @description
 */
public interface UserMapper {


    /**
     * 根据 id 查询数据
     *
     * @param id id
     * @return {@link User}
     */
    User selectByPrimaryKey(Serializable id);

}