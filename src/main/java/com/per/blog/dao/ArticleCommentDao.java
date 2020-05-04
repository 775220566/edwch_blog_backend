package com.per.blog.dao;

import com.per.blog.pojo.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by Administrator on 2020/2/10 0010.
 */
public interface ArticleCommentDao extends JpaRepository<ArticleComment,String>,JpaSpecificationExecutor<ArticleComment> {

    List<ArticleComment> findByArticleId(String id);

}