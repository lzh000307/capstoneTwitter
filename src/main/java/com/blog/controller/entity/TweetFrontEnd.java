package com.blog.controller.entity;

import com.blog.pojo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.blog.util.Converter.tagsToIds;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetFrontEnd {

    private User user = new User();
    private Tweet tweet = new Tweet();
    private String tagIds;
    //获取多个标签的id
    private List<Tweet> tweets = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private List<String> imgUnits = new ArrayList<>();
    private Integer imgNum;

    public Integer getImgNum() {
        return imgNum;
    }

    public void setImgNum(Integer imgNum) {
        this.imgNum = imgNum;
    }

    public List<String> getImgUnits() {
        return imgUnits;
    }

    public void setImgUnits(List<String> imgUnits) {
        this.imgUnits = imgUnits;
    }

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
}
