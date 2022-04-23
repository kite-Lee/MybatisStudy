package com.lee.dao;

import com.lee.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    // 查询全部用户
    List<User> getUserList();

    // 根据 id 查询用户
    User getUserById(int id);

    // 插入一个新用户
    int addUser(User user);

    // 修改用户
    int updateUser(User user);

    // 删除用户
    int deleteUser(int id);

    // 实体类或者数据库中的表，字段或者参数过多，考虑使用map

    // 修改用户信息  update 使用 map
    int updateUserByMap(Map<String, Object> map);

    // 插入新用户信息  insert 使用 map
    int addUserByMap(Map<String, Object> map);

    // 查询使用 map
    User getUserByMapId(Map<String, Object> map);

    // 模糊查询
    List<User> getUserLike(String value);

}
