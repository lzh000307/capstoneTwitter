package com.blog.pojo;

import java.io.Serializable;

/**
 * (Trend)实体类
 *
 * @author makejava
 * @since 2022-05-17 15:33:25
 */
public class UserCollection implements Serializable {
//    private static final long serialVersionUID = -18899882816310315L;
    
    private Long tweetId;
    
    private Long userId;


    @Override
    public String toString() {
        return "UserCollection{" +
                "tweetId=" + tweetId +
                ", userId=" + userId +
                '}';
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
