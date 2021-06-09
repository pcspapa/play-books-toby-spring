package com.cspark.play.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

  @Bean
  UserDao userDDao() {
    return new UserDao(new DConnectionMaker());
  }

  @Bean
  UserDao userNDao() {
    return new UserDao(new NConnectionMaker());
  }
}