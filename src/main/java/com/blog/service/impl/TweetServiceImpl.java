package com.blog.service.impl;

import com.blog.dao.TweetDao;
import com.blog.exception.NotFoundException;
import com.blog.pojo.Blog;
import com.blog.pojo.BlogAndTag;
import com.blog.pojo.Tag;
import com.blog.pojo.Tweet;
import com.blog.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetDao tweetDao;

    @Override
    public Tweet getTweet(Long id) {
        Tweet tweet = tweetDao.queryById(id);
        if(tweet==null){
            throw new NotFoundException("该推文不存在");
        }
        return tweet;
    }

    @Override
    public List<Tweet> searchAllTweet(Tweet tweet) {
        return tweetDao.searchAllTweet(tweet);
    }

    @Override
    public List<Tweet> getIndex() {
        return tweetDao.getByDesc();
    }

    @Override
    public void viewPlusOne(Tweet tweet) {
        tweet.setViews(tweet.getViews()+1);
        tweetDao.update(tweet);
    }

    @Override
    public List<Tweet> getAllTweet() {
        return tweetDao.getAllTweet();
    }

    @Override
    public void saveTweet(Tweet tweet) {
        tweet.setCreateTime(new Date());
        tweet.setUpdateTime(new Date());
        tweet.setViews(0);
        //保存博客
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
