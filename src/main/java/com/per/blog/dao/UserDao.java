package com.per.blog.dao;

import com.per.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by Administrator on 2020/2/8 0008.
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User> {
    User findByUserNameAndPassword(String userName, String password);
}
