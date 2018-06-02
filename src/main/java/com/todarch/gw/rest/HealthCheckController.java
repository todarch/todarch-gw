package com.todarch.gw.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

  @GetMapping("/non-secured/up")
  public String up() {
    return "I am Todarch Gateway Service, up and running";
  }
}

