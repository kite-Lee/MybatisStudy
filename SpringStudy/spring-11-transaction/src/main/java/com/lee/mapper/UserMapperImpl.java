package com.lee.mapper;

import com.lee.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper{



    @Override
    public List<User> selectUser() {
        User user = new User(4, "张三", "012345");
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        userMapper.addUser(user);
        userMapper.deleteUser(6);
        return userMapper.selectUser();

//        return getSqlSession().getMapper(UserMapper.class).selectUser();
    }

    @Override
    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }

}
