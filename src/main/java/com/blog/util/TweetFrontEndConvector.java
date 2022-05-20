package com.blog.util;

import com.blog.controller.entity.TweetFrontEnd;
import com.blog.pojo.Tweet;
import com.blog.pojo.TweetImg;
import com.blog.service.TagService;
import com.blog.service.TrendService;
import com.blog.service.TweetImgService;
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
    @Autowired
    TweetImgService tweetImgService;

    public TweetFrontEnd convertToTweetFrontEnd(Tweet tweet) {
        TweetFrontEnd tweetFrontEnd = new TweetFrontEnd();
        tweetFrontEnd.setTweet(tweet);
        tweetFrontEnd.setUser(userService.findById(tweet.getUserId()));
        //寻找tagList
        trendService.getTagByTweetId(tweet.getId());
        //寻找图片列表
        List<String> imgUnits = tweetImgService.getImgUrl(tweet.getId());
        tweetFrontEnd.setImgUnits(imgUnits);
        //获取图片数量
        tweetFrontEnd.setImgNum(imgUnits.size());
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
