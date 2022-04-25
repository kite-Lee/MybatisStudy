package com.lee.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
// @Component 表示这个类由Spring 管理
@Component
public class User {
    private String name;

    public String getName() {
        return name;
    }

    @Value("Kite")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
