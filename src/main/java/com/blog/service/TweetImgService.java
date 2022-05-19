package com.blog.service;

import java.util.List;

public interface TweetImgService {
    public int deleteByTweetId(Long tweetId);
    public List<String> getImgUrl(Long tweetId);
    public int save(Long tweetId, String imgUrl);



}
