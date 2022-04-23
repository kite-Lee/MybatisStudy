package com.lee.pojo;

import lombok.Data;

import java.util.List;

// 用来测试一对多
@Data
public class Teacher {

    private int id;
    private String name;

    private List<Student> students;
}
