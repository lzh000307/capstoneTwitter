package com.blog;

import com.blog.dao.UserDao;
import com.blog.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserDaoTest {

    @Autowired
    private UserDao userDao;
    @Test
    void testGetById() {
        User user1 = userDao.getById(1L);

        //insert
        User testUser = new User();
        testUser.setUsername("test2");
        testUser.setPassword("test2");
        testUser.setEmail("test2@test2.com");
        testUser.setNickname("test2");
        testUser.setAvatar("test2");
        testUser.setStatus(1);
        testUser.setPhoneNumber("20086");

//        userDao.insert(testUser);
        System.out.println(userDao.getById(1L));
        //update
//        userDao.addUser(testUser);
        testUser.setId(4L);
        System.out.println(userDao.getById(4L));
        testUser.setUsername("test2-set");
        testUser.setPhoneNumber("20087");
        testUser.setStatus(2);
        userDao.updateUser(testUser);
        System.out.println(userDao.getById(4L));
    }

}
