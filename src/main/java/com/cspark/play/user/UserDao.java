package com.cspark.play.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserDao {

  private JdbcContext jdbcContext;

  public void setJdbcContext(JdbcContext jdbcContext) {
    this.jdbcContext = jdbcContext;
  }

  public void add(User user) throws SQLException {
    jdbcContext.jdbcContextWithStatementStrategy(new StatementStrategy() {
      @Override
      public PreparedStatement makePrepareStatement(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        return ps;
      }
    });
  }

  public User get(String id) throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    User findUser = null;
    try {
      conn = jdbcContext.dataSource.getConnection();

      ps = conn.prepareStatement("select * from users where id = ?");
      ps.setString(1, id);

      rs = ps.executeQuery();
      if (rs.next()) {
        findUser = new User(
            rs.getString("id"),
            rs.getString("name"),
            rs.getString("password")
        );
      }

      if (Objects.isNull(findUser))
        throw new EmptyResultDataAccessException(1);

      return findUser;
    } catch (SQLException e) {
      throw e;
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException e) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException e) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException e) {
        }
    }
  }

  public void deleteAll() throws SQLException {
    jdbcContext.executeSql("delete from users");
  }

  public int getCount() throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      conn = jdbcContext.dataSource.getConnection();

      ps = conn.prepareStatement("select count(*) from users");

      rs = ps.executeQuery();
      rs.next();

      return rs.getInt(1);
    } catch (SQLException e) {
      throw e;
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException e) {
        }
      if (ps != null)
        try {
          ps.close();
        } catch (SQLException e) {
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException e) {
        }
    }
  }

}
