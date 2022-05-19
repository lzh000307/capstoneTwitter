package com.blog.dao;

import com.blog.pojo.TweetImg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TweetImg)表数据库访问层
 *
 * @author Elvis Lin
 * @since 2022-05-19 22:26:08
 */
@Mapper
@Repository
public interface TweetImgDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TweetImg queryById(Long id);

    /**
     * 通过TweetID查询单条数据
     *
     * @param TweetId
     * @return List<String>
     */
    @Select("select img_url from tweet_img where tweet_id = #{TweetId}")
    List<String> queryImgByTweetId(Long TweetId);

    /**
     * 根据tweetId删除所有图片
     */
    @Delete("delete from tweet_img where tweet_id = #{tweetId}")
    int deleteByTweetId(Long tweetId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TweetImg> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tweetImg 实例对象
     * @return 对象列表
     */
    List<TweetImg> queryAll(TweetImg tweetImg);

    /**
     * 新增数据
     *
     * @param tweetImg 实例对象
     * @return 影响行数
     */
    int insert(TweetImg tweetImg);

    /**
     * 修改数据
     *
     * @param tweetImg 实例对象
     * @return 影响行数
     */
    int update(TweetImg tweetImg);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
