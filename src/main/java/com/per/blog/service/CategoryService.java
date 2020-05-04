package com.per.blog.service;

import com.per.blog.dao.CategoryDao;
import com.per.blog.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2020/2/26 0026.
 */
@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao;

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

}
