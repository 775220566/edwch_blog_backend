package com.per.blog.pojo;


import net.bytebuddy.agent.builder.AgentBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="pl_article_comment")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ArticleComment {

  @Id
  @GeneratedValue(generator = "jpa-uuid")
  @Column(length = 32)
  private String id;
  private String context;
  @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
  @JoinColumn(name="user_id")//设置在article表中的关联字段(外键)
  private User user;
  private String articleId;
  private java.sql.Timestamp createTime;
  private String parentCommentId;
  private Integer thumbs;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }


  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }


  public String getArticleId() {
    return articleId;
  }

  public void setArticleId(String articleId) {
    this.articleId = articleId;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public String getParentCommentId() {
    return parentCommentId;
  }

  public void setParentCommentId(String parentCommentId) {
    this.parentCommentId = parentCommentId;
  }


  public Integer getThumbs() {
    return thumbs;
  }

  public void setThumbs(Integer thumbs) {
    this.thumbs = thumbs;
  }

}
