package com.lee.dao;

import com.lee.dao.UserMapper;
import com.lee.pojo.User;
import com.lee.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {

    @Test
    public void getUserListTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserList();

        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }
    @Test
    public void getUserByIdTest() {
        // 示例
        int id = 1;

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(id);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void addUserTest() {
        User user = new User(4, "David","456789");

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int num1 = userMapper.addUser(user);
        System.out.println("num1 : " + num1);
        int num2 = userMapper.addUser(new User(5,"Emily", "567890"));
        System.out.println("num2 : " + num2);

        if (num1 > 0) {
            System.out.println("插入成功");
        }
        // 不提交就不会真的改变数据库
        // sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateUserTest() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int i = userMapper.updateUser(new User(5, "Friday", "678910"));
        if (i > 0) {
            System.out.println("修改成功");
        }

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void deleteUserTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int i = userMapper.deleteUser(5);
        if (i > 0) {
            System.out.println("删除成功");
        }
    }
}
