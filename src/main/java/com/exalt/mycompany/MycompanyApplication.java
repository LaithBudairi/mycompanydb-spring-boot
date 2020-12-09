package com.exalt.mycompany;

import com.exalt.mycompany.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)

public class MycompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycompanyApplication.class, args);
    }

}
