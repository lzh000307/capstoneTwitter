package com.blog.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Long id;
    private String name;
    private int blogNum; // 博客数量，不作为数据库使用，作为传到前端的一个变量

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(int blogNum) {
        this.blogNum = blogNum;
    }
}
