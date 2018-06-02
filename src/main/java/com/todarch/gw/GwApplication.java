package com.todarch.gw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
public class GwApplication {

  public static void main(String[] args) {
    SpringApplication.run(GwApplication.class, args);
  }
}
