package com.blog.service;

import com.blog.pojo.Blog;
import com.blog.pojo.Censorship;
import com.blog.pojo.Tweet;

import java.util.List;

public interface TweetService {
    List<Tweet> getAllTweet();

    void saveTweet(Tweet tweet);

    void updateTweet(Tweet tweet);

    void deleteTweet(Long id);

    Tweet getTweet(Long id);

    List<Tweet> searchAllTweet(Tweet tweet);

    List<Tweet> getIndex();

    void viewPlusOne(Tweet tweet);

    List<Tweet> sortByLike(List<Tweet> tweets);

    List<Tweet> getTweetsByUserId(Long userId);

    public void updateStatusByUserId(Long userId, Integer originStatus, Integer afterStatus);

    public void censorshipAPI(Censorship censorship);

    public void removeCensorship(int id);
}
