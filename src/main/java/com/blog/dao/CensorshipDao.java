package com.blog.dao;

import com.blog.pojo.Censorship;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CensorshipDao {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Censorship queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Censorship> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<Censorship> queryAll();

    /**
     * 新增数据
     *
     * @param censorship 实例对象
     * @return 影响行数
     */
    int insert(Censorship censorship);

    /**
     * 修改数据
     *
     * @param censorship 实例对象
     * @return 影响行数
     */
    int update(Censorship censorship);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    @Delete("delete from censorship where word=#{word}")
    int deleteCensorshipByWord(String word);

    @Delete("delete from censorship where id=#{id}")
    int deleteCensorshipById(Integer id);

    @Select("select * from censorship where word=#{word}")
    Censorship getCensorshipFromWord(String word);

    @Select("select * from censorship")
    List<Censorship> getAllCensorship();

    @Select("select * from censorship where id=#{id}")
    Censorship getCensorshipFromId(Integer id);

    @Select("select * from censorship where word like '%#{word}%'")
    List<Censorship> getCensorshipFromWordLike(String word);
}
