package com.per.blog.pojo;

import java.util.List;

/**
 * Created by Administrator on 2020/2/29 0029.
 */
public class CommentVO {
    private ArticleComment comment;
    private List<ArticleComment> subComment;

    public ArticleComment getComment() {
        return comment;
    }

    public void setComment(ArticleComment comment) {
        this.comment = comment;
    }

    public List<ArticleComment> getSubComment() {
        return subComment;
    }

    public void setSubComment(List<ArticleComment> subComment) {
        this.subComment = subComment;
    }
}
