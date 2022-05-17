package com.blog.pojo;

import java.io.Serializable;

/**
 * (Trend)实体类
 *
 * @author makejava
 * @since 2022-05-17 15:33:25
 */
public class Trend implements Serializable {
//    private static final long serialVersionUID = -18899882816310315L;
    
    private Long tweetId;
    
    private Long tagId;

    @Override
    public String toString() {
        return "Trend{" +
                "tweetId=" + tweetId +
                ", tagId=" + tagId +
                '}';
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

}
