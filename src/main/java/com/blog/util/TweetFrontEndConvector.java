package com.blog.util;

import com.blog.controller.entity.TweetFrontEnd;
import com.blog.dao.UserDao;
import com.blog.pojo.Tweet;
import com.blog.service.TagService;
import com.blog.service.TrendService;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class TweetFrontEndConvector {
    @Autowired
    TagService tagService;
    @Autowired
    TrendService trendService;
    @Autowired
    UserService userService;

    public TweetFrontEnd convertToTweetFrontEnd(Tweet tweet) {
        TweetFrontEnd tweetFrontEnd = new TweetFrontEnd();
        tweetFrontEnd.setTweet(tweet);
        tweetFrontEnd.setUser(userService.findById(tweet.getUserId()));
        //寻找tagList
        trendService.getByTweetId(tweet.getId());
        return tweetFrontEnd;
    }
}
