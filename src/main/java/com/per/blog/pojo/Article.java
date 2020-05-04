package com.per.blog.pojo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="pl_article")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Article {

  @Id
  @GeneratedValue(generator = "jpa-uuid")
  @Column(length = 32)
  private String id;
  private String title;
  @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
  @JoinColumn(name="user_id")//设置在article表中的关联字段(外键)
  private User user;
  private Integer isTop;
  @Column(name = "category_id")
  private Integer categoryId;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private Integer score;
  private String context;
  private Integer isRead;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public User getUser() {
    return user;
  }

  public void setUserId(User userId) {
    this.user = user;
  }

  public Integer getIsTop() {
    return isTop;
  }

  public void setIsTop(Integer isTop) {
    this.isTop = isTop;
  }


  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }


  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }


  public Integer getIsRead() {
    return isRead;
  }

  public void setIsRead(Integer isRead) {
    this.isRead = isRead;
  }

}
