package com.cspark.play.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class UserDaoTest {

  private final DaoFactory daoFactory = new DaoFactory();

  @Test
  void addAndGetNUser() throws SQLException, ClassNotFoundException {  // Once?
    UserDao dao = daoFactory.userNDao();

    dao.add(new User("mj", "Mary Jane Watson", "pw"));

    User mjUser = dao.get("mj");
    assertThat(mjUser.getId()).isEqualTo("mj");
    assertThat(mjUser.getName()).isEqualTo("Mary Jane Watson");
    assertThat(mjUser.getPassword()).isEqualTo("pw");
  }

  @Test
  void addAndGetDUser() throws SQLException, ClassNotFoundException {  // Once?
    UserDao dao = daoFactory.userDDao();

    dao.add(new User("bp", "Brad Pitt", "pw"));

    User mjUser = dao.get("bp");
    assertThat(mjUser.getId()).isEqualTo("bp");
    assertThat(mjUser.getName()).isEqualTo("Brad Pitt");
    assertThat(mjUser.getPassword()).isEqualTo("pw");
  }

}
