package com.lee.pojo;

import lombok.Data;

// 用来测试多对一
@Data
public class Student {

    private int id;
    private String name;
    private Teacher teacher;
}
