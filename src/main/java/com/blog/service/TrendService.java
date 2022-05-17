package com.blog.service;

import com.blog.pojo.Tag;

import java.util.List;

public interface TrendService {
    void updateTrends(List<Long> tagIds, Long tweetId);
    void deleteByTweetId(Long tweetId);

    public List<Tag> getTagByTweetId(Long tweetId);

    List<Long> getTweetIdByTagId(Long tagId);

    int countTweetNumByTagId(Long tagId);
}
