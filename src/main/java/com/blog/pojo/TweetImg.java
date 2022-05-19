package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (TweetImg)实体类
 *
 * @author Elvis Lin
 * @since 2022-05-19 22:26:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetImg implements Serializable {
//    private static final long serialVersionUID = -34660681941536552L;
    
    private Long id;
    
    private Long tweetId;
    
    private String imgUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
