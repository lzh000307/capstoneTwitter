package com.blog.controller.entity;

import com.blog.pojo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.blog.util.Converter.tagsToIds;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetFrontEnd {

//    private Long id;
//    private String title;
//    private String content;
//    private String firstPicture;
//    private String status;
//    private Integer views;
//    private Integer likes;
//    private boolean commentable;
//    private Integer recommend;
//    private boolean published;
//    private boolean like;
//    private Date createTime;
//    private Date updateTime;
//    private Long typeId;
//    private Long userId;


    private User user;
    private Tweet tweet;
    private String tagIds;
    //获取多个标签的id
    private List<Tweet> tweets = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
