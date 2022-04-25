package com.lee.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// @Component 等价于 <bean id="user" class="com.lee.pojo.User"/>

@Component
@Scope("singleton")     // 单例模式      prototype
public class User {

    @Value("Lee")       // 简单的可以使用注解形式
    public String name;

    public String getName() {
        return name;
    }
    @Value("Lee")  // 同上
    public void setName(String name) {
        this.name = name;
    }
}
