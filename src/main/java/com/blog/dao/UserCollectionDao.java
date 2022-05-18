package com.blog.dao;

import com.blog.pojo.Like;
import com.blog.pojo.UserCollection;
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
public interface UserCollectionDao {

    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    @Select("select * from `user_collection` where user_id = #{userId} and tweet_id = #{tweetId}")
    UserCollection get(@Param("userId") Long userId, @Param("tweetId") Long tweetId);

    @Select("select * from `user_collection`")
    List<UserCollection> queryAll();

    /**
     * 新增数据
     *
     * @return 影响行数
     */
    @Insert("insert into `user_collection`(user_id, tweet_Id) values(#{userId}, #{tweetId})")
    void insert(Long userId, Long tweetId);

    /**
     * 通过主键删除数据
     *
     * @param  userId, tweetId
     * @return 影响行数
     */
    @Select("delete from `user_collection` where user_id = #{userId} and tweet_Id = #{tweetId}")
    void delete(@Param("userId") Long userId, @Param("tweetId") Long tweetId);

    @Select("select * from `user_collection` where tweet_Id = #{tweetId}")
    List<UserCollection> getByTweetId(@Param("tweetId") Long tweetId);

    @Select("select * from `user_collection` where user_id = #{userId}")
    List<UserCollection> getByUserId(@Param("userId") Long userId);

}
