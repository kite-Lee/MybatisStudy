package com.lee.dao;


import com.lee.pojo.Teacher;
import com.lee.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;



public class DaoTest {

    @Test
    public void getTeacherAllInfoTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher =  teacherMapper.getTeacherAllInfo(1);

        System.out.println(teacher);
    }

    @Test
    public void getTeacherAllInfo2Test() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher =  teacherMapper.getTeacherAllInfo2(1);

        System.out.println(teacher);
    }

}
