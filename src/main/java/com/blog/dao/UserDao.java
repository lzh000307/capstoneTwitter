package com.blog.dao;

import com.blog.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    User queryByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from user where username = #{username}")
    User queryByUsername(String username);

    @Select("select * from user where id = #{id}")
    User findById(Long id);

//    @Insert("insert into user(username, "+
//            "password, "+
//            "nickname, " +
//            "email, "+
//            "phone_number, " +
//            "avatar, "+
//            "status, " +
//            "create_time, "+
//            "update_time) values("+"" +
//            "#{username}, "+
//            "#{password}, "+
//            "#{nickname}" +
//            "#{email}, " +
//            "#{phoneNumber}, "+"" +
//            "#{avatar}, "+
//            "#{status}, " +
//            "#{createTime}, "+"" +
//            "#{updateTime})")
    int addUser(User user);

    int updateUser(User user);

    @Delete("delete from user where id = #{id}")
    int delete(Long id);
    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id = #{id}")
    User getById(Long id);

    @Update("update user set password = #{password}, update_time = #{updateTime} where id = #{id}")
    int updatePassword(User user);


}
