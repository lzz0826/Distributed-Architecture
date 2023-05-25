package org.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class UserApplication {



  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
    System.out.println("Hello world!");
  }
}
