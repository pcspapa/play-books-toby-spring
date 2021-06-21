package com.cspark.play.user;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {

  @Bean
  UserDao userDao() {
    UserDao dao = new UserDao();
    dao.setDataSource(dataSource());

    return dao;
  }

  @Bean
  DataSource dataSource() {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
    dataSource.setDriverClass(org.h2.Driver.class);
    dataSource.setUrl("jdbc:h2:tcp://localhost/~/toby-spring");
    dataSource.setUsername("sa");
    dataSource.setPassword("");

    return dataSource;
  }

}