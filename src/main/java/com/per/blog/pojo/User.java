package com.per.blog.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="pl_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User {

  @Id
  @GeneratedValue(generator = "jpa-uuid")
  @Column(length = 32)
  private String id;
  private String userName;
  private String password;
  private String avatars;
  private String description;
  private Integer thumbs;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getAvatars() {
    return avatars;
  }

  public void setAvatars(String avatars) {
    this.avatars = avatars;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public Integer getThumbs() {
    return thumbs;
  }

  public void setThumbs(Integer thumbs) {
    this.thumbs = thumbs;
  }

}
