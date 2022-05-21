package com.blog.dao;

import com.blog.pojo.Comment;
import com.blog.pojo.Tweet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (CommentLe)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-18 15:50:33
 */
@Mapper
@Repository
public interface CommentDao {

    /**
     * 通过TweetId查询主评论，按时间升序排列
     */
    @Select("select * from comment where tweet_id = #{tweetId} and parent_comment_id = -1 and status < 2000 order by create_time asc")
    List<Comment> selectMainCommentByTweetId(@Param("tweetId") Long tweetId);
    /**
     * 通过TweetId和ParentCommentId查询子评论，按时间升序排列
     */
    @Select("select * from comment where tweet_id = #{tweetId} and parent_comment_id = #{parentCommentId} order by create_time asc")
    List<Comment> selectSubCommentByTweetIdAndParentCommentId(@Param("tweetId") Long tweetId, @Param("parentCommentId") Long parentCommentId);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Comment> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param comment 实例对象
     * @return 对象列表
     */
    List<Comment> queryAll(Comment comment);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int insert(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    @Select("select * from comment where parent_comment_id = #{parentCommentId} and status < 2000 order by create_time asc")
    List<Comment> selectSubCommentByParentCommentId(Long parentCommentId);
    @Select("select * from comment")
    List<Comment> getAll();
    @Select("select * from comment where status = #{status}")
    List<Comment> queryByStatus(Integer status);
}
