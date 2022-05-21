package com.blog.pojo;

import java.io.Serializable;

/**
 * (Censorship)实体类
 *
 * @author makejava
 * @since 2022-05-21 17:17:06
 */
public class Censorship implements Serializable {
//    private static final long serialVersionUID = -67015027883877866L;
    
    private Integer id;
    
    private String word;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
