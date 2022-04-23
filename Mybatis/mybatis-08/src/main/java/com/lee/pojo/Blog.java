package com.lee.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Blog {

    private String id;
    private String title;
    private String author;
    private Date createTime;      // 属性和字段名不一致
    private int views;

}
