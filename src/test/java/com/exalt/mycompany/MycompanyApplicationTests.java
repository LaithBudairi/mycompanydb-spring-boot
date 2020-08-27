package com.exalt.mycompany;

import com.exalt.mycompany.controller.UserController;
import com.exalt.mycompany.dto.UserDTO;
import com.exalt.mycompany.model.User;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class MycompanyApplicationTests {

    private TestRestTemplate testRestTemplate;
    private  Logger logger;
    @LocalServerPort
    private int port;


    @BeforeEach
    void contextLoads() {
        testRestTemplate = new TestRestTemplate();
        logger = LoggerFactory.getLogger(MycompanyApplicationTests.class);
    }

    @Test
    public void testGetAllUsers_RetrievesAllExistingUsersFromDB_RetrievedSuccessfully200() throws URISyntaxException {
        URI uri = new URI("http://localhost:" + port + "/api/v1/users/");
        ResponseEntity <UserDTO[]>res = testRestTemplate.getForEntity(uri, UserDTO[].class);
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
        logger.info(res.getBody() + "");
    }

    @Test
    public void testGetUserWithID_RetrievesUserWithIDFromDB_RetrievedSuccessfully200() throws URISyntaxException {
        // adduser
        User expectedUser = createUser();

        URI uri = new URI("http://localhost:" + port + "/api/v1/users/" + 1);
        ResponseEntity <UserDTO> res = testRestTemplate.getForEntity(uri, UserDTO.class);

        User actualUser = new ModelMapper().map(res.getBody(), User.class);

        Assertions.assertEquals(expectedUser.getId(), actualUser.getId());
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void testCreateNewUser_CreatesNewUserInDB_CreatedSuccessfully200() throws URISyntaxException {
        URI uri = new URI("http://localhost:" + port + "/api/v1/users/");

        // Creating user
        User user = new User();
        user.setUserName("koko koko");
        user.setEmail("koko@gmail.com");
        user.setPassword("kokokokoko");

        HttpEntity<User> request = new HttpEntity<>(user);


        ResponseEntity<User> res = testRestTemplate.postForEntity(uri, request, User.class);

        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    private User createUser() throws URISyntaxException{
        User user = new User();
        user.setId(1);
        user.setUserName("lolo lolo");
        user.setEmail("lolo@gmail.com");
        user.setPassword("lolo");

        HttpEntity<User> request = new HttpEntity<>(user);

        URI uri = new URI("http://localhost:" + port + "/api/v1/users/");

        ResponseEntity<User> res = testRestTemplate.postForEntity(uri, request, User.class);

        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());

        return user;
    }

}
