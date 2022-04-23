package com.lee.dao;

import com.lee.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

    // 插入数据
    int addBlog(Blog blog);

    // 查询博客
    List<Blog> queryBlogIF(Map map);

    List<Blog> queryBlogWhere(Map map);

    List<Blog> queryBlogChoose(Map map);

    int updateBlog(Map map);

    // 查询第 1 2 3 号记录的博客 （foreach）
    List<Blog> queryBlogForeach(Map map);

}
