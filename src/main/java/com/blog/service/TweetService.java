package com.blog.service;

import com.blog.pojo.Blog;
import com.blog.pojo.Tweet;

import java.util.List;

public interface TweetService {
    List<Tweet> getAllTweet();

    void saveTweet(Tweet tweet);

    void updateTweet(Tweet tweet);

    void deleteTweet(Long id);

    Tweet getTweet(Long id);

    List<Tweet> searchAllTweet(Tweet tweet);
}
