package com.lee.dao;

import com.lee.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();


    @Select("select *  from user where id = #{id}")
    User getUserById(@Param("id") int id);
    // @Param("id") 中的 id 才是传递的参数， int id 中的 id 只是形参


    @Insert("insert into user(id, name, pwd) values (#{id}, #{name}, #{password})")
    int addUser(User user);


    @Update("update user set name = #{name}, pwd = #{password} where id = #{id}")
    int updateUser(User user);


    @Delete("delete from user where id = #{uid}")
    int deleteUser(@Param("uid") int id);




}
