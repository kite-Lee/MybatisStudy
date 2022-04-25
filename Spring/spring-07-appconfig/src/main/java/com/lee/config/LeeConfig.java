package com.lee.config;

import com.lee.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


// @Configuration 也被 Component 标记注释 这个类也由Spring 管理
@Configuration      // 表示这是一个配置类
@ComponentScan("com.lee.pojo")
@Import(LeeConfig2.class)
public class LeeConfig {

    @Bean       // 注册一个 bean 方法名等同于 id 属性 返回值等同于 class 属性
    public User getUser() {
        return new User();
    }
}
