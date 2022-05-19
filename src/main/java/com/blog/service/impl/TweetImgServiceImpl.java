package com.blog.service.impl;

import com.blog.dao.TweetImgDao;
import com.blog.pojo.TweetImg;
import com.blog.service.TweetImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetImgServiceImpl implements TweetImgService {
    @Autowired
    private TweetImgDao tweetImgDao;

    @Override
    public int deleteByTweetId(Long tweetId) {
        return tweetImgDao.deleteByTweetId(tweetId);
    }

    @Override
    public List<String> getImgUrl(Long tweetId) {
        return tweetImgDao.queryImgByTweetId(tweetId);
    }
    @Override
    public int save(Long tweetId, String imgUrl) {
        TweetImg tweetImg = new TweetImg();
        tweetImg.setTweetId(tweetId);
        tweetImg.setImgUrl(imgUrl);
        return tweetImgDao.insert(tweetImg);
    }
}


