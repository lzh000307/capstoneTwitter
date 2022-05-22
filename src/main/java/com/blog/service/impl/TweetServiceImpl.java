package com.blog.service.impl;

import com.blog.dao.TweetDao;
import com.blog.exception.NotFoundException;
import com.blog.pojo.*;
import com.blog.service.CensorshipService;
import com.blog.service.TweetService;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetDao tweetDao;
    @Autowired
    private UserService userService;
    @Autowired
    private CensorshipService censorshipService; // 屏蔽词服务

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
        return tweetDao.getByDesc2();
    }

    @Override
    public void viewPlusOne(Tweet tweet) {
        tweet.setViews(tweet.getViews()+1);
        tweetDao.update(tweet);
    }

    @Override
    public List<Tweet> sortByLike(List<Tweet> tweets) {
        Collections.sort(tweets, (o1, o2) -> o2.getLikes() - o1.getLikes());
        if(tweets.size()>10){
            return tweets.subList(0,10);
        }
        return tweets;
    }

    @Override
    public List<Tweet> getTweetsByUserId(Long userId) {
        return tweetDao.queryByUserId(userId);
    }

    /**
     * 只改动状态一样的推文，不改动由其他原因屏蔽的推文
     * @param userId
     * @param originStatus
     * @param afterStatus
     */
    @Override
    public void updateStatusByUserId(Long userId, Integer originStatus, Integer afterStatus) {
        //找到List<Tweet>
        List<Tweet> tweets = tweetDao.queryByUserId(userId);
        for (Tweet tweet : tweets) {
            if(tweet.getStatus().equals(originStatus)) {
                tweet.setStatus(afterStatus);
                tweetDao.update(tweet);
            }
        }
    }

    @Override
    public void censorshipAPI(Censorship censorship) {
        List<Tweet> tweets = tweetDao.getAllTweet();
        String word = censorship.getWord();
        Integer status = censorship.getId() + 4000;
        //查找所有包含该词的推文
        for (Tweet tweet : tweets) {
            if(tweet.getContent().contains(word)||tweet.getTitle().contains(word)){
                tweet.setStatus(status);
                tweetDao.update(tweet);
            }
        }
    }

    @Override
    public void removeCensorship(int id) {
        Integer status = id + 4000;
        List<Tweet> tweets = tweetDao.queryByStatus(id+4000);
        //恢复推文
        for (Tweet tweet : tweets) {
            tweet.setStatus(userService.findById(tweet.getUserId()).getStatus());
            //反查有无其他敏感词
            int newStatus = censorshipService.censor(tweet.getContent() + " " + tweet.getTitle());
            if(newStatus>0){
                newStatus = newStatus + 4000;
                tweet.setStatus(newStatus);
            }
            //更新推文
            tweetDao.update(tweet);
        }
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
        //敏感词审查
        int status = censorshipService.censor(tweet.getContent() + " " + tweet.getTitle());
        if(status>0){
            status = status + 4000;
            tweet.setStatus(status);
        }
        //保存博客
        tweetDao.insert(tweet);
    }

    @Override
    public void updateTweet(Tweet tweet) {
        //敏感词审查
        int status = censorshipService.censor(tweet.getContent() + " " + tweet.getTitle());
        if(status>0){
            status = status + 4000;
            tweet.setStatus(status);
        }else{
            //没有发现关键词
            tweet.setStatus(userService.findById(tweet.getUserId()).getStatus());
        }
        tweetDao.update(tweet);
    }

    @Override
    public void deleteTweet(Long id) {
        tweetDao.deleteById(id);
    }
}
