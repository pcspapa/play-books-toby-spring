package com.cspark.play.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import com.cspark.play.user.DaoFactory;
import com.cspark.play.user.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class SingletonTest {

  @Test
  void factoryObject() {
    DaoFactory factory = new DaoFactory();
    UserDao dao1 = factory.userNDao();
    UserDao dao2 = factory.userNDao();

    System.out.println("dao1 = " + dao1);
    System.out.println("dao2 = " + dao2);

    assertThat(dao1).isNotEqualTo(dao2);
  }

  @Test
  void springBean() {
    ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
    UserDao dao1 = context.getBean("userNDao", UserDao.class);
    UserDao dao2 = context.getBean("userNDao", UserDao.class);

    System.out.println("dao1 = " + dao1);
    System.out.println("dao2 = " + dao2);

    assertThat(dao1).isEqualTo(dao2);
  }
}
