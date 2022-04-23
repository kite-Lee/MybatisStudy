package com.lee.dao;

import com.lee.pojo.Student;

import java.util.List;


public interface StudentMapper {

    // 查询所有学生的信息，以及对应老师的信息
    // 按照查询嵌套
    public List<Student> getStudent();

    // 按照结果查询
    public List<Student> getStudent2();


}
