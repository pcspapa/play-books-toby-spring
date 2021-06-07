package com.cspark.play.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class UserDaoTest {

  @Test
  void addAndGetUser() throws SQLException, ClassNotFoundException {  // Once?
    UserDao dao = new UserDao();

    dao.add(new User("mj", "Mary Jane Watson", "pw"));

    User mjUser = dao.get("mj");
    assertThat(mjUser.getId()).isEqualTo("mj");
    assertThat(mjUser.getName()).isEqualTo("Mary Jane Watson");
    assertThat(mjUser.getPassword()).isEqualTo("pw");
  }
}
