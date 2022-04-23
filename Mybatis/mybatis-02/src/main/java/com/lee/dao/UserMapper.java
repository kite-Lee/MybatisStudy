package com.lee.dao;

import com.lee.pojo.User;
import java.util.List;

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


}
