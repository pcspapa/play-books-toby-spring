package com.cspark.play.user;

import java.util.Objects;
import lombok.Getter;

@Getter
public class User {

  private final String id;
  private final String name;
  private final String password;

  public User(String id, String name, String password) {
    this.id = id;
    this.name = name;
    this.password = password;
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
