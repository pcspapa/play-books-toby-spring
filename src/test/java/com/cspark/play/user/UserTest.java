package com.cspark.play.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  void createUser() {
    User user = new User("id", "name", "password");

    assertThat(user).isNotNull();
    assertThat(user.getId()).isEqualTo("id");
    assertThat(user.getName()).isEqualTo("name");
    assertThat(user.getPassword()).isEqualTo("password");
  }

  @Test
  void equalAndHash() {
    User user = new User("id", "name", "password");
    User renameUser = new User("id", "rename", "password");

    assertThat(user).isEqualTo(renameUser);
  }
}
