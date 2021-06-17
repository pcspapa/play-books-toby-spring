package com.cspark.play.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy {

  @Override
  public PreparedStatement makePrepareStatement(Connection conn) throws SQLException {
    PreparedStatement ps;
    ps = conn.prepareStatement("delete from users");
    return ps;
  }
}