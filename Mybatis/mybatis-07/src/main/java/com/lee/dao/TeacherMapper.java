package com.lee.dao;

import com.lee.pojo.Student;
import com.lee.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {

    // 获得指定老师名下的所有学生及老师的信息
    // 按查询嵌套
    Teacher getTeacherAllInfo(@Param("tid") int id);

    // 获得指定老师名下的所有学生及老师的信息
    // 按结果嵌套
    Teacher getTeacherAllInfo2(@Param("tid") int id);

}

