package com.cspark.play.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

  @Autowired
  UserDao userDao;

  @Autowired
  UserService userService;
  private List<User> users;

  @BeforeEach
  void setUp() {
    users = Arrays.asList(
        new User("id1", "name1", "pw1", Level.BASIC, 49, 0),
        new User("id2", "name2", "pw2", Level.BASIC, 50, 0),
        new User("id3", "name3", "pw3", Level.SILVER, 60, 29),
        new User("id4", "name4", "pw4", Level.SILVER, 60, 30),
        new User("id5", "name5", "pw5", Level.GOLD, 100, 100)
    );
  }

  @Test
  void bean() {
    assertThat(userService).isNotNull();
  }

  @Test
  void upgradeLevels() {
    userDao.deleteAll();

    for (User user : users) {
      userDao.add(user);
    }

    userService.upgradeLevels();

    checkLevel(users.get(0), Level.BASIC);
    checkLevel(users.get(1), Level.SILVER);
    checkLevel(users.get(2), Level.SILVER);
    checkLevel(users.get(3), Level.GOLD);
    checkLevel(users.get(4), Level.GOLD);
  }

  private void checkLevel(User user, Level expectedLevel) {
    User getUser = userDao.get(user.getId());
    assertThat(getUser.getLevel()).isEqualTo(expectedLevel);
  }
}
