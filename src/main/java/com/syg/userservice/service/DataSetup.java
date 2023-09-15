package com.syg.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

@Service
public class DataSetup implements CommandLineRunner {

    @Value("classpath:h2/data.sql")
    private Resource dataSql;

    @Autowired
    private R2dbcEntityTemplate r2dbcEntityTemplate;
    @Override
    public void run(String... args) throws Exception {
      String query = StreamUtils.copyToString(dataSql.getInputStream(), StandardCharsets.UTF_8);
      System.out.println("create table Query: "+ query);
      this.r2dbcEntityTemplate
              .getDatabaseClient()
              .sql(query)
              .then()
              .subscribe();

    }
}
