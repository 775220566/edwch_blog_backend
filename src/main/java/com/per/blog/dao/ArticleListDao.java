package com.per.blog.dao;

import com.per.blog.pojo.ArticleListVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * Created by Administrator on 2020/2/26 0026.
 */
public interface ArticleListDao  extends JpaRepository<ArticleListVO,String>,JpaSpecificationExecutor<ArticleListVO> {
    List<ArticleListVO> findTop5ByOrderByThumbsAsc();

    List<ArticleListVO> findTop5ByOrderByCreateTimeAsc();
}
