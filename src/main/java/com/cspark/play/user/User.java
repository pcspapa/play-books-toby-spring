package com.cspark.play.user;

import java.util.Objects;
import lombok.Getter;

@Getter
public class User {

  private String id;
  private String name;
  private String password;
  private Level level;
  private int login;
  private int recommend;

  public User(String id, String name, String password, Level level, int login, int recommend) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.level = level;
    this.login = login;
    this.recommend = recommend;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setLevel(Level level) {
    this.level = level;
  }

  public void setLogin(int login) {
    this.login = login;
  }

  public void setRecommend(int recommend) {
    this.recommend = recommend;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    User user = (User) o;
    return id.equals(user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
