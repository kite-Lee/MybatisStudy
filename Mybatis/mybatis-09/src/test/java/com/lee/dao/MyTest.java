package com.lee.dao;

import com.lee.pojo.User;
import com.lee.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Map;

public class MyTest {

    @Test
    public void test() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        User user = userMapper.queryUserById(1);
        System.out.println(user);

        // userMapper.updateUser(new User(2, "newName", "newPwd"));
        // 手动清理缓存
        // sqlSession.clearCache();
        sqlSession.close();

        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = userMapper2.queryUserById(1);
        System.out.println(user2);
        System.out.println(user == user2);
        sqlSession2.close();
    }
}
