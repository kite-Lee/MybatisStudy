package com.lee.dao;

import com.lee.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    // 根据 id 查询用户
    User queryUserById(@Param("id") int id);

    // 修改用户
    int updateUser(User user);
}
