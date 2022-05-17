package com.blog.service.impl;

import com.blog.dao.TrendDao;
import com.blog.pojo.Tag;
import com.blog.pojo.Trend;
import com.blog.service.TagService;
import com.blog.service.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrendServiceImpl implements TrendService {

    @Autowired
    private TrendDao trendDao;

    @Autowired
    private TagService tagService;


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

    @Override
    public List<Tag> getTagByTweetId(Long tweetId) {
        List<Trend> tagStr = trendDao.getByTweetId(tweetId);
        //转换为Tag
        List<Tag> tags = new ArrayList<>();
        for (Trend trend : tagStr) {
            tags.add(tagService.getTag(trend.getTagId()));
        }
        return tags;
    }

    @Override
    public List<Long> getTweetIdByTagId(Long tagId) {
        List<Long> tweetIds = new ArrayList<>();
        List<Trend> trends = trendDao.getByTagId(tagId);
        for (Trend trend : trends) {
            tweetIds.add(trend.getTweetId());
        }
        return tweetIds;
    }

    @Override
    public int countTweetNumByTagId(Long tagId) {
        List<Trend> trends = trendDao.getByTagId(tagId);
        if(trends==null){
            return 0; //没有tweet
        }
        return trends.size();
    }


}
