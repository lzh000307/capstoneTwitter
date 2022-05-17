package com.blog.dao;

import com.blog.pojo.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Topic)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-18 00:16:00
 */
@Mapper
@Repository
public interface TopicDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Topic queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Topic> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<Topic> queryAll();

    /**
     * 新增数据
     *
     * @param topic 实例对象
     * @return 影响行数
     */
    int insert(Topic topic);

    /**
     * 修改数据
     *
     * @param topic 实例对象
     * @return 影响行数
     */
    int update(Topic topic);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
    @Select("select * from topic where name = #{name}")
    Topic queryByName(String name);
}
