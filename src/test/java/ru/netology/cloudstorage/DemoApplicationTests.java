package ru.netology.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.netology.cloudstorage.domain.FileInfo;
import ru.netology.cloudstorage.service.JwtRequest;

import java.util.List;
import java.util.Map;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    @Container
    private static final GenericContainer<?> myApp = new GenericContainer<>("cloud:latest").withExposedPorts(8080);

    @BeforeAll
    public static void setUp() {
    }

    @Test
    void contextLoads() {

    }

    @Test
    void getLoginResponseTest() {

        JwtRequest authRequest = new JwtRequest();
        authRequest.setLogin("user1");
        authRequest.setPassword("user1");
        ResponseEntity<?> response = restTemplate.postForEntity("http://localhost:" + myApp.getMappedPort(8080) + "/login", authRequest, Map.class);
        Assertions.assertNotNull(response);

    }

    @Test
    void getListResponseTest() {

        Integer limit = 3;
        ResponseEntity<?> response = restTemplate.postForEntity("http://localhost:" + myApp.getMappedPort(8080) + "/list", limit, List.class);
        Assertions.assertNotNull(response);

    }

}
