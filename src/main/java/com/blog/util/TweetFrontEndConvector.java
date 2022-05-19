package com.blog.util;

import com.blog.controller.entity.TweetFrontEnd;
import com.blog.pojo.Tweet;
import com.blog.service.TagService;
import com.blog.service.TrendService;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
        trendService.getTagByTweetId(tweet.getId());
        return tweetFrontEnd;
    }
    public List<TweetFrontEnd> convertToTweetFrontEnd(List<Tweet> tweets){
        List<TweetFrontEnd> tweetFrontEnds = new java.util.ArrayList<>();
        for(Tweet tweet:tweets){
            tweetFrontEnds.add(convertToTweetFrontEnd(tweet));
        }
        return tweetFrontEnds;
    }
}
