package com.blog.pojo;

import java.io.Serializable;

/**
 * (Topic)实体类
 *
 * @author makejava
 * @since 2022-05-18 00:15:57
 */
public class Topic implements Serializable {
//    private static final long serialVersionUID = -40464586091076335L;
    
    private Long id;
    
    private String name;


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

}
