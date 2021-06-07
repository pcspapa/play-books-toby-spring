package com.cspark.play.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao {

  @Override
  protected Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName("org.h2.Driver");
    return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/toby-spring", "sa", "");
  }
}
