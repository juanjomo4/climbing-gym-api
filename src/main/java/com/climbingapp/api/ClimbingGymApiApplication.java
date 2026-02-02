package com.climbingapp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.climbingapp.api.controller",
    "com.climbingapp.api.service",
    "com.climbingapp.api.serviceImpl",
    "com.climbingapp.api.repository",
    "com.climbingapp.api.config",
    "com.climbingapp.api.exception"
})
public class ClimbingGymApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClimbingGymApiApplication.class, args);
    }
}
