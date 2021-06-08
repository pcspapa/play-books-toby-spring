package com.cspark.play.user;

public class DaoFactory {

  public DaoFactory() {
  }

  UserDao userDDao() {
    return new UserDao(new DConnectionMaker());
  }

  UserDao userNDao() {
    return new UserDao(new NConnectionMaker());
  }
}