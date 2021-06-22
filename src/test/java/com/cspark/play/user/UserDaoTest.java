package com.cspark.play.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

@SpringBootTest
class UserDaoTest {

  @Autowired
  private UserDao dao;

  @Autowired
  private DataSource datasource;

  private User user1;
  private User user2;
  private User user3;
  private User mjUser;

  @BeforeEach
  void setUp() {
    user1 = new User("id1", "name1", "pw1");
    user2 = new User("id2", "name2", "pw2");
    user3 = new User("id3", "name3", "pw3");
    mjUser = new User("mj", "Mary Jane Watson", "pw");
  }

  @Test
  void addAndGetUser() {
    dao.deleteAll();
    assertThat(dao.getCount()).isZero();

    dao.add(mjUser);

    User getMJUser = dao.get(mjUser.getId());
    assertThat(getMJUser.getId()).isEqualTo(mjUser.getId());
    assertThat(getMJUser.getName()).isEqualTo(mjUser.getName());
    assertThat(getMJUser.getPassword()).isEqualTo(mjUser.getPassword());
  }

  @Test
  void getUserFailure() {
    assertThatThrownBy(() -> dao.get("unknown"))
        .isInstanceOf(EmptyResultDataAccessException.class);
  }

  @Test
  void duplicateKey() {
    dao.deleteAll();

    dao.add(user1);
    assertThatThrownBy(() -> dao.add(user1))
        .isInstanceOf(DuplicateKeyException.class);

    try {
      dao.add(user1);
    } catch (DuplicateKeyException ex) {
      SQLException sqlEx = (SQLException) ex.getRootCause();
      SQLErrorCodeSQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(datasource);

      assertThat(set.translate(null, null, sqlEx))
          .isInstanceOf(DuplicateKeyException.class);
    }

  }

  @Test
  void count() {
    dao.deleteAll();
    assertThat(dao.getCount()).isZero();

    dao.add(user1);
    assertThat(dao.getCount()).isEqualTo(1);

    dao.add(user2);
    assertThat(dao.getCount()).isEqualTo(2);

    dao.add(user3);
    assertThat(dao.getCount()).isEqualTo(3);
  }

  @Test
  void getAll() {
    dao.deleteAll();

    List<User> users = dao.getAll();
    assertThat(users.size()).isZero();

    dao.add(user1);
    List<User> users1 = dao.getAll();
    assertThat(users1.size()).isEqualTo(1);
    checkSameUser(users1.get(0), user1);

    dao.add(user2);
    List<User> users2 = dao.getAll();
    assertThat(users2.size()).isEqualTo(2);
    checkSameUser(users2.get(0), user1);
    checkSameUser(users2.get(1), user2);

    dao.add(user3);
    List<User> users3 = dao.getAll();
    assertThat(users3.size()).isEqualTo(3);
    checkSameUser(users3.get(0), user1);
    checkSameUser(users3.get(1), user2);
    checkSameUser(users3.get(2), user3);
  }

  private void checkSameUser(User actual, User expected) {
    assertThat(actual.getId()).isEqualTo(expected.getId());
    assertThat(actual.getName()).isEqualTo(expected.getName());
    assertThat(actual.getPassword()).isEqualTo(expected.getPassword());
  }
}
