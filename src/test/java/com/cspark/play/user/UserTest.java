package com.cspark.play.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  void createUser() {
    User user = new User("id", "name", "password", Level.BASIC, 1, 0);

    assertThat(user).isNotNull();
    assertThat(user.getId()).isEqualTo("id");
    assertThat(user.getName()).isEqualTo("name");
    assertThat(user.getPassword()).isEqualTo("password");
  }

  @Test
  void equalAndHash() {
    User user = new User("id", "name", "password", Level.BASIC, 1, 0);
    User renameUser = new User("id", "rename", "password", Level.BASIC, 1, 0);

    assertThat(user).isEqualTo(renameUser);
  }
}
