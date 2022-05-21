package com.blog.dao;

import com.blog.pojo.Censorship;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CensorshipDao {
    @Insert("insert into user(word) values(#{word})")
    int addCensorship(String word);

    @Delete("delete from user where word=#{word}")
    int deleteCensorshipByWord(String word);

    @Delete("delete from user where id=#{id}")
    int deleteCensorshipById(int id);

    @Update("update user set word=#{word} where id=#{id}")
    int updateCensorship(int id, String newWord);

    @Select("select * from user where word=#{word}")
    Censorship getCensorshipFromWord(String word);

    @Select("select * from user")
    List<Censorship> getAllCensorship();

    @Select("select * from user where id=#{id}")
    Censorship getCensorshipFromId(int id);

    @Select("select * from user where word like '%#{word}%'")
    List<Censorship> getCensorshipFromWordLike(String word);
}
