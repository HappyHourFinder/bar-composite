package com.mathieuaime.hhf.bar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BarCompositeApplication {

  public static void main(String[] args) {
    SpringApplication.run(BarCompositeApplication.class, args);
  }
}
