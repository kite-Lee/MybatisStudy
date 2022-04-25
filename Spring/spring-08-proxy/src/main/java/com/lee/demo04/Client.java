package com.lee.demo04;


public class Client {

    public static void main(String[] args) {

        // 真实角色
        UserServiceImpl userService = new UserServiceImpl();
        // UserServiceImplTWO 如果实现了，也可以代理
        // UserServiceImplTWO userService2 = new UserServiceImplTWO();

        // 代理角色， 不存在
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        // 设置要代理的对象
        pih.setTarget(userService);

        // 动态生成代理类
        UserService proxy = (UserService) pih.getProxy();
        proxy.add();
    }
}
