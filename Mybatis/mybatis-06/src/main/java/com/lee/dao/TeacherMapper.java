package com.lee.dao;

import com.lee.pojo.Student;
import com.lee.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {

    @Select("select * from teacher where id = #{tid}")
    Teacher getTeacher(@Param("tid") int id);

//    public Teacher getTeacher(int id);
}

