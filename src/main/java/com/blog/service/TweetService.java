package com.blog.service;

import com.blog.pojo.Censorship;
import com.blog.pojo.Tweet;

import java.util.List;

public interface TweetService {
    List<Tweet> getAllTweet();

    void saveTweet(Tweet tweet);    //保存一条微博

    void updateTweet(Tweet tweet);  //更新一条微博

    void deleteTweet(Long id);      //删除一条微博

    Tweet getTweet(Long id);        //获取一条微博

    List<Tweet> searchAllTweet(Tweet tweet);    //搜索微博

    List<Tweet> getIndex();         //获取首页微博

    void viewPlusOne(Tweet tweet);  //浏览量+1

    List<Tweet> sortByLike(List<Tweet> tweets); //按照点赞数排序，用于实现点赞榜

    List<Tweet> getTweetsByUserId(Long userId); //获取某个用户的微博
    //根据userid来更新微博status，用于实现一键屏蔽封禁用户推文的功能
    void updateStatusByUserId(Long userId, Integer originStatus, Integer afterStatus);
    //供censorshipService调用，实现屏蔽推文
    void censorshipAPI(Censorship censorship);
    //移除敏感词的时候调用，此方法会先解除标记为该
    void removeCensorship(int id);
}
