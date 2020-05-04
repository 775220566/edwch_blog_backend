package com.per.blog.service;

import com.per.blog.dao.ArticleCommentDao;
import com.per.blog.pojo.ArticleComment;
import com.per.blog.pojo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/2/10 0010.
 */
@Service
public class ArticleCommentService {

    @Autowired
    ArticleCommentDao articleCommentDao;


    public List<CommentVO> findByArtId(String id) {
        List<CommentVO> comments = new ArrayList();
        List<ArticleComment> articleCommentList = articleCommentDao.findByArticleId(id);
        for (ArticleComment ac:articleCommentList) {
            if (ac.getParentCommentId() == null || "".equals(ac.getParentCommentId())) {
                CommentVO commentVO = new CommentVO();
                commentVO.setComment(ac);
                commentVO.setSubComment(new ArrayList<>());
                comments.add(commentVO);
            }
        }

        for (CommentVO c:comments) {
            String commentId = c.getComment().getId();

            for (ArticleComment ac:articleCommentList) {
                if (ac.getParentCommentId() != null && !"".equals(ac.getParentCommentId())) {
                    if (commentId.equals(ac.getParentCommentId())) {
                        c.getSubComment().add(ac);
                    }
                }
            }
        }

        return comments;
    }

    public void add(ArticleComment articleComment) {
        articleCommentDao.save(articleComment);
    }
}
