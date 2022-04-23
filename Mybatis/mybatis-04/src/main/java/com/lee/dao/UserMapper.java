package com.lee.dao;

import com.lee.pojo.User;

import java.util.List;
import java.util.Map;


public interface UserMapper {

    // 查询全部用户
    User getUserById(int id);

    // 分页
    List<User> getUserByLimit(Map<String, Integer> map);
    // 分页
    List<User> getUserByRowBounds();
}
