package com.cspark.play.user;

import java.util.List;

public interface UserDao {

  void add(User user);

  User get(String id);

  void deleteAll();

  int getCount();

  List<User> getAll();

  void update(User user);
}
