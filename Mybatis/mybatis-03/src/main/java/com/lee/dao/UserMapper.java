package com.lee.dao;

import com.lee.pojo.User;


public interface UserMapper {

    // 查询全部用户
    User getUserById(int id);

}
