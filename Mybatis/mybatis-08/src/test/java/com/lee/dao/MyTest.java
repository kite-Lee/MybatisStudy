package com.lee.dao;

import com.lee.pojo.Blog;
import com.lee.utils.IDUtils;
import com.lee.utils.MybatisUtils;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class MyTest {
    @Test
    public void addBlogTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setId(IDUtils.getId());
        blog.setTitle("Mybatis学习");
        blog.setAuthor("Kite");
        blog.setCreateTime(new Date());
        blog.setViews(9999);
        int i1 = blogMapper.addBlog(blog);

        blog.setId(IDUtils.getId());
        blog.setTitle("Maven学习");
        int i2 = blogMapper.addBlog(blog);

        blog.setId(IDUtils.getId());
        blog.setTitle("Spring学习");
        int i3 = blogMapper.addBlog(blog);

        blog.setId(IDUtils.getId());
        blog.setTitle("SpringMVC学习");
        int i4 = blogMapper.addBlog(blog);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void queryBlogIFTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        Map<String, String> map = new HashMap<>();

        map.put("title", "Mybatis学习");
        List<Blog> blogList = blogMapper.queryBlogIF(map);
        for (Blog blog : blogList) {
            System.out.println(blog);
        }

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void queryBlogWhereTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        Map<String, String> map = new HashMap<>();

        map.put("title", "Mybatis学习");
        List<Blog> blogList = blogMapper.queryBlogWhere(map);
        for (Blog blog : blogList) {
            System.out.println(blog);
        }

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void queryBlogChooseTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        Map<String, String> map = new HashMap<>();

        map.put("title", "SpringMVC学习");
        map.put("author", "Lee");
        map.put("views", "9999");
        List<Blog> blogList = blogMapper.queryBlogChoose(map);
        for (Blog blog : blogList) {
            System.out.println(blog);
        }

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateBlogTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        Map<String, String> map = new HashMap<>();

        map.put("title", "SpringMVC学习");
        map.put("author", "Lee");
        map.put("id", "a6e18ce7fed64103aa294c31cd9b78a6");
        int j = blogMapper.updateBlog(map);

        sqlSession.commit();
        sqlSession.close();

    }


    @Test
    public void queryBlogForeachTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        HashMap map = new HashMap<>();
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);


        map.put("ids", ids);

        List<Blog> blogList = blogMapper.queryBlogForeach(map);
        for (Blog blog : blogList) {
            System.out.println(blog);
        }
        sqlSession.commit();
        sqlSession.close();

    }
}
