package com.blog.dao;

import com.blog.pojo.Tweet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Tweet)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-17 12:20:39
 */
@Mapper
@Repository
public interface TweetDao {




    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Tweet queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Tweet> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tweet 实例对象
     * @return 对象列表
     */
    List<Tweet> queryAll(Tweet tweet);

    /**
     * 新增数据
     *
     * @param tweet 实例对象
     * @return 影响行数
     */
    int insert(Tweet tweet);

    /**
     * 修改数据
     *
     * @param tweet 实例对象
     * @return 影响行数
     */
    int update(Tweet tweet);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    @Select("select * from tweet")
    List<Tweet> getAllTweet();

    List<Tweet> searchAllTweet(Tweet tweet);
    List<Tweet> getByDesc();
    @Select("select * from tweet where status<2000 and published=1" +
            " order by update_time desc")
    List<Tweet> getByDesc2();
    @Select("select * from tweet where user_id = #{userId} order by update_time desc")
    List<Tweet> queryByUserId(Long userId);
    @Select("select * from tweet where status = #{status}")
    List<Tweet> queryByStatus(Integer status);
}
