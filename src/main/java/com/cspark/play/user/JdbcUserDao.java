package com.cspark.play.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcUserDao implements UserDao {

  private final RowMapper<User> rowMapper = new RowMapper<User>() {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new User(
          rs.getString("id"),
          rs.getString("name"),
          rs.getString("password")
      );
    }
  };

  private JdbcTemplate jdbcTemplate;

  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public void add(User user) {
    jdbcTemplate.update("insert into users(id, name, password) values(?, ?, ?)",
        user.getId(),
        user.getName(),
        user.getPassword());
  }

  @Override
  public User get(String id) {
    return jdbcTemplate.queryForObject("select * from users where id = ?", rowMapper, id);
  }

  @Override
  public void deleteAll() {
    jdbcTemplate.update("delete from users");
  }

  @Override
  public int getCount() {
    return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
  }

  @Override
  public List<User> getAll() {
    return jdbcTemplate.query("select * from users", rowMapper);
  }
}
