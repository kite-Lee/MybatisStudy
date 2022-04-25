package com.lee.demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 使用这个类自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {

    // 被代理的接口
    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    // 生成得到代理类
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
    }

    // 处理代理实例，并返回结果
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        seeHouse();
        fare();
        // 动态代理的本质就是使用反射机制实现的
        return method.invoke(rent, args);
    }

    // 看房
    public void seeHouse() {
        System.out.println("中介带你看房");
    }

    // 签合同
    public void signContract() {
        System.out.println("签租赁合同");
    }

    // 收中介费
    public void fare() {
        System.out.println("收中介费");
    }

}
