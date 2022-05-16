package com.blog.dao;

import com.blog.pojo.Tweet;
import com.blog.pojo.BlogAndTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TweetDao {

    Tweet getTweet(Long id);  //后台展示博客

    Tweet getDetailedTweet(@Param("id") Long id);  //博客详情

    List<Tweet> getAllTweet();

    List<Tweet> getByTypeId(Long typeId);  //根据类型id获取博客

    List<Tweet> getByTagId(Long tagId);  //根据标签id获取博客

    List<Tweet> getIndexTweet();  //主页博客展示

    List<Tweet> getAllRecommendTweet();  //推荐博客展示

    List<Tweet> getSearchTweet(String query);  //全局搜索博客

    List<Tweet> searchAllTweet(Tweet tweet);  //后台根据标题、分类、推荐搜索博客

    List<String> findGroupYear();  //查询所有年份，返回一个集合

    List<Tweet> findByYear(@Param("year") String year);  //按年份查询博客

    int saveTweet(Tweet tweet);

    int saveTweetAndTag(BlogAndTag tweetAndTag);

    int updateTweet(Tweet tweet);

    int deleteTweet(Long id);

}
