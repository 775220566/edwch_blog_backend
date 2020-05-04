package com.per.blog.dao;

import com.per.blog.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2020/2/26 0026.
 */
public interface CategoryDao extends JpaRepository<Category,String>,JpaSpecificationExecutor<Category> {
}
