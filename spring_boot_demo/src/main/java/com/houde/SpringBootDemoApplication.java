package com.houde;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
另外的启动方式
mvn spring-boot:run

mvn install
java -jar xx.jar

 */
@SpringBootApplication
public class SpringBootDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
