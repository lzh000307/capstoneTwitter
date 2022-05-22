package com.blog.service.impl;
import com.blog.dao.UserCollectionDao;
import com.blog.pojo.Tweet;
import com.blog.pojo.UserCollection;
import com.blog.service.TweetService;
import com.blog.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCollectionServiceImpl implements UserCollectionService {

    @Autowired
    private UserCollectionDao userCollectionDao;
    @Autowired
    private TweetService tweetService;

    @Override
    public void addUserCollection(Long userId, Long tweetId) {
        userCollectionDao.insert(userId, tweetId);
    }

    @Override
    public void deleteUserCollection(Long userId, Long tweetId) {
        userCollectionDao.delete(userId, tweetId);
    }

    @Override
    public boolean isUserCollection(Long userId, Long tweetId) {
        UserCollection like = userCollectionDao.get(userId, tweetId);
        return like != null;
    }

    private List<UserCollection> getUserCollections(Long id) {
        return userCollectionDao.getByUserId(id);
    }

    @Override
    public List<Tweet> getUserCollectionTweets(Long id) {
        //取得收藏列表
        List<UserCollection> userCollections = getUserCollections(id);
        //通过收藏列表得到推文列表
        List<Tweet> collectTweet = new ArrayList<>();
        for(UserCollection userCollection : userCollections){
            Tweet tweet = tweetService.getTweet(userCollection.getTweetId());
            Integer status = tweet.getStatus();
            if(status > 2000 || !tweet.isPublished()){
                //不是自己收藏自己的情况
                if(!id.equals(tweet.getUserId())){
                    continue; //跳过
                }
            }
            collectTweet.add(tweet);
        }
        return collectTweet;
    }

    @Override
    public void deleteByTweetId(Long id) {
        userCollectionDao.deleteByTweetId(id);
    }
}
