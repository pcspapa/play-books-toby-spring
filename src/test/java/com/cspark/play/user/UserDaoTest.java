package com.cspark.play.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class UserDaoTest {

  @Test
  void addAndGetNUser() throws SQLException, ClassNotFoundException {  // Once?
    ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
    UserDao dao = context.getBean("userNDao", UserDao.class);

    dao.add(new User("mj", "Mary Jane Watson", "pw"));

    User mjUser = dao.get("mj");
    assertThat(mjUser.getId()).isEqualTo("mj");
    assertThat(mjUser.getName()).isEqualTo("Mary Jane Watson");
    assertThat(mjUser.getPassword()).isEqualTo("pw");
  }

  @Test
  void addAndGetDUser() throws SQLException, ClassNotFoundException {  // Once?
    ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
    UserDao dao = context.getBean("userDDao", UserDao.class);

    dao.add(new User("bp", "Brad Pitt", "pw"));

    User mjUser = dao.get("bp");
    assertThat(mjUser.getId()).isEqualTo("bp");
    assertThat(mjUser.getName()).isEqualTo("Brad Pitt");
    assertThat(mjUser.getPassword()).isEqualTo("pw");
  }

}
