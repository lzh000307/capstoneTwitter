package com.blog.dao;

import com.blog.pojo.Like;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Trend)表数据库访问层
 *
 * @author Elvis Lin
 * @since 2022-05-17 15:15:04
 */
@Mapper
@Repository
public interface LikeDao {

    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    @Select("select * from `like` where user_id = #{userId} and tweet_id = #{tweetId}")
    Like get(@Param("userId") Long userId, @Param("tweetId") Long tweetId);

    @Select("select * from `like`")
    List<Like> queryAll(Like like);

    /**
     * 新增数据
     *
     * @return 影响行数
     */
    @Insert("insert into `like`(user_id, tweet_Id) values(#{userId}, #{tweetId})")
    void insert(Long userId, Long tweetId);

    /**
     * 通过主键删除数据
     *
     * @param  userId, tweetId
     * @return 影响行数
     */
    @Select("delete from `like` where user_id = #{userId} and tweet_Id = #{tweetId}")
    void delete(@Param("userId") Long userId, @Param("tweetId") Long tweetId);

    @Select("select * from `like` where tweet_Id = #{tweetId}")
    List<Like> getByTweetId(@Param("tweetId") Long tweetId);

    @Select("select * from `like` where user_id = #{userId}")
    List<Like> getByTagId(@Param("userId") Long userId);

}
