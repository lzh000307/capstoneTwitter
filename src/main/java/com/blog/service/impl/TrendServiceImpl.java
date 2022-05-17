package com.blog.service.impl;

import com.blog.dao.TrendDao;
import com.blog.pojo.Tag;
import com.blog.service.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrendServiceImpl implements TrendService {

    @Autowired
    private TrendDao trendDao;

    @Override
    public void updateTrends(List<Long> tagIds, Long tweetId) {
        //把当前tweetId的tags全删除
        trendDao.deleteAllByTweetId(tweetId);
        //insertTags
        for (Long tagId : tagIds) {
            trendDao.insert(tagId, tweetId);
        }
    }

    @Override
    public void deleteByTweetId(Long tweetId) {
        trendDao.deleteAllByTweetId(tweetId);
    }


}
