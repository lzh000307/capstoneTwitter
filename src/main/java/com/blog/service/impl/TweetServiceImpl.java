package com.blog.service.impl;

import com.blog.dao.TweetDao;
import com.blog.pojo.Tweet;
import com.blog.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetDao tweetDao;

    @Override
    public Tweet getTweet(Long id) {
        return tweetDao.queryById(id);
    }
    @Override
    public List<Tweet> getAllTweet() {
        return tweetDao.getAllTweet();
    }

    @Override
    public void saveTweet(Tweet tweet) {
        tweetDao.insert(tweet);
    }

    @Override
    public void updateTweet(Tweet tweet) {
        tweetDao.update(tweet);
    }

    @Override
    public void deleteTweet(Long id) {
        tweetDao.deleteById(id);
    }
}
