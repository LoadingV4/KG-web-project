package org.team2project.camealone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan(basePackages = "org.team2project.camealone.member")
public class CameAloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(CameAloneApplication.class, args);
    }

}
