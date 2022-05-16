package com.blog.dao;

import com.blog.pojo.UserAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserAdminDao {

    UserAdmin queryByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
