package com.cspark.play.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

  public void add(User user) throws ClassNotFoundException, SQLException {
    Class.forName("org.h2.Driver");
    Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/toby-spring", "sa", "");

    PreparedStatement ps = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
    ps.setString(1, user.getId());
    ps.setString(2, user.getName());
    ps.setString(3, user.getPassword());

    ps.executeUpdate();

    ps.close();
    conn.close();
  }

  public User get(String id) throws ClassNotFoundException, SQLException {
    Class.forName("org.h2.Driver");
    Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/toby-spring", "sa", "");

    PreparedStatement ps = conn.prepareStatement("select * from users where id = ?");
    ps.setString(1, id);

    ResultSet rs = ps.executeQuery();
    rs.next();

    return new User(
        rs.getString("id"),
        rs.getString("name"),
        rs.getString("password")
    );
  }
}
