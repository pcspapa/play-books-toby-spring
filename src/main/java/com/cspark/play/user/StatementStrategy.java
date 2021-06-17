package com.cspark.play.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {

  PreparedStatement makePrepareStatement(Connection conn) throws SQLException;
}
