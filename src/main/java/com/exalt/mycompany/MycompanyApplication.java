package com.exalt.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class MycompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycompanyApplication.class, args);
    }

}
