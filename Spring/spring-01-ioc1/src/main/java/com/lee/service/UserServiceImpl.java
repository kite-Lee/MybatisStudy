package com.lee.service;

import com.lee.dao.UserDao;
import com.lee.dao.UserDaoImpl;
import com.lee.dao.UserDaoMysqlImpl;
import com.lee.dao.UserDaoOracleImpl;

public class UserServiceImpl implements UserService{

//    private UserDao userDao = new UserDaoImpl();
//    想要使用不同业务 MySQL 和 Oracle ，要修改代码如下
//    private UserDao userDao = new UserDaoMysqlImpl();
//    private UserDao userDao = new UserDaoOracleImpl();


    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {

        userDao.getUser();
    }

}
