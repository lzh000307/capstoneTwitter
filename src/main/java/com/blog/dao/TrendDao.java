package com.blog.dao;

import com.blog.pojo.Trend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Trend)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-17 15:15:04
 */
@Mapper
@Repository
public interface TrendDao {

    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    @Select("select * from trend where tag_Id = #{tagId} and tweet_Id = #{tweetId}")
    Trend get(@Param("tagId") Long tagId, @Param("tweetId") Long tweetId);

    @Select("select * from trend")
    List<Trend> queryAll(Trend trend);

    /**
     * 新增数据
     *
     * @return 影响行数
     */
    @Insert("insert into trend(tag_Id, tweet_Id) values(#{tagId}, #{tweetId})")
    void insert(Long tagId, Long tweetId);

    /**
     * 通过主键删除数据
     *
     * @param  tagId, tweetId
     * @return 影响行数
     */
    @Select("delete from trend where tag_Id = #{tagId} and tweet_Id = #{tweetId}")
    void deleteById(@Param("tagId") Long tagId, @Param("tweetId") Long tweetId);

    @Select("select * from trend where tweet_Id = #{tweetId}")
    List<Trend> getByTweetId(@Param("tweetId") Long tweetId);

    @Select("select * from trend where tag_Id = #{tagId}")
    List<Trend> getByTagId(@Param("tagId") Long tagId);

    //根据tweetId删除全部tag
    @Select("delete from trend where tweet_Id = #{tweetId}")
    void deleteAllByTweetId(@Param("tweetId") Long tweetId);
}
