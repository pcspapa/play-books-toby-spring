package com.cspark.play.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
class UserDaoTest {

  @Autowired
  private ApplicationContext context;
  private UserDao dao;

  @BeforeEach
  void setUp() {
    dao = context.getBean("userDao", UserDao.class);
  }

  @Test
  void addAndGetUser() throws SQLException {
    User user = new User("mj", "Mary Jane Watson", "pw");

    dao.deleteAll();
    assertThat(dao.getCount()).isEqualTo(0);

    dao.add(user);

    User mjUser = dao.get(user.getId());
    assertThat(mjUser.getId()).isEqualTo(user.getId());
    assertThat(mjUser.getName()).isEqualTo(user.getName());
    assertThat(mjUser.getPassword()).isEqualTo(user.getPassword());
  }

  @Test
  void getUserFailure() {
    assertThatThrownBy(() -> dao.get("unknown"))
        .isInstanceOf(EmptyResultDataAccessException.class);
  }

  @Test
  void count() throws SQLException {
    User user1 = new User("id1", "name1", "pw1");
    User user2 = new User("id2", "name2", "pw2");
    User user3 = new User("id3", "name3", "pw3");

    dao.deleteAll();
    assertThat(dao.getCount()).isEqualTo(0);

    dao.add(user1);
    assertThat(dao.getCount()).isEqualTo(1);

    dao.add(user2);
    assertThat(dao.getCount()).isEqualTo(2);

    dao.add(user3);
    assertThat(dao.getCount()).isEqualTo(3);
  }
}
