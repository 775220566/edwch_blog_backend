package com.per.blog.service;

import com.per.blog.dao.UserDao;
import com.per.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2020/2/8 0008.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User login(String userName, String password) {
        return userDao.findByUserNameAndPassword(userName, password);
    }

    public User findUserById(String id) {
        return userDao.findById(id).get();
    }

    public User addUser(User user) {
        return userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
