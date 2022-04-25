package com.lee.dao;

public class UserDaoMysqlImpl implements UserDao{

    @Override
    public void getUser() {
        System.out.println("MySQL获取用户数据");
    }
}
