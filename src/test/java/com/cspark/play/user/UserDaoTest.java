package com.cspark.play.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
class UserDaoTest {

  @Autowired
  private UserDao dao;

  @Test
  void addAndGetUser() {
    User user = new User("mj", "Mary Jane Watson", "pw");

    dao.deleteAll();
    assertThat(dao.getCount()).isZero();

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
  void count() {
    User user1 = new User("id1", "name1", "pw1");
    User user2 = new User("id2", "name2", "pw2");
    User user3 = new User("id3", "name3", "pw3");

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

    User user1 = new User("id1", "name1", "pw1");
    User user2 = new User("id2", "name2", "pw2");
    User user3 = new User("id3", "name3", "pw3");

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
