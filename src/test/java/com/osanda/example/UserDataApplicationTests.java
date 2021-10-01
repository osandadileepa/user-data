package com.osanda.example;

import com.osanda.example.controller.UserController;
import com.osanda.example.repository.CityRepository;
import com.osanda.example.repository.UserRepository;
import com.osanda.example.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserDataApplicationTests {

    @Container
    public static MySQLContainer mysql = new MySQLContainer("mysql:5.7.30")
            .withPassword("inmemory")
            .withUsername("inmemory");

    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CityRepository cityRepository;

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.username", mysql::getUsername);
    }

    @Test
    void allBeanCheck() {
        Assertions.assertNotNull(this.userController);
        Assertions.assertNotNull(this.userService);
        Assertions.assertNotNull(this.userRepository);
        Assertions.assertNotNull(this.cityRepository);
    }


}
