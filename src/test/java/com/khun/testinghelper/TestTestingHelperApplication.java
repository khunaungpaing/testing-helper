package com.khun.testinghelper;

import org.springframework.boot.SpringApplication;

public class TestTestingHelperApplication {

    public static void main(String[] args) {
        SpringApplication.from(TestingHelperApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
