package com.cspark.play.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

  @Bean
  public UserDao userDDao() {
    return new UserDao(new DConnectionMaker());
  }

  @Bean
  public UserDao userNDao() {
    return new UserDao(new NConnectionMaker());
  }
}