package com.blog.service;

import com.blog.pojo.Tag;

import java.util.List;

public interface TrendService {
    void updateTrends(List<Long> tagIds, Long tweetId);
    void deleteByTweetId(Long tweetId);

    public List<Tag> getByTweetId(Long tweetId);
}
