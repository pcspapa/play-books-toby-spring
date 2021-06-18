package com.cspark.play.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

public class JdbcContext {

  DataSource dataSource;

  public JdbcContext() {
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
    Connection conn = null;
    PreparedStatement ps = null;

    try {
      conn = dataSource.getConnection();

      ps = stmt.makePrepareStatement(conn);

      ps.executeUpdate();
    } catch (SQLException e) {
      throw e;
    } finally {
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