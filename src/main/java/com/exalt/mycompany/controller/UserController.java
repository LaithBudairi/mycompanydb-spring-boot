package com.exalt.mycompany.controller;

import com.exalt.mycompany.model.User;
import com.exalt.mycompany.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping(value = "api/v1/")
@RestController
public class UserController {

    private UserService userService;
    private final static Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @RequestMapping("logger")
    public String getLogs() {
        logger.error("I am error");
        logger.warn("I am warning");
        logger.info("I am info");
        logger.debug("I am debug");
        logger.debug("I am trace");

        return "Welcome to API";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public List<User> getUsers() {
        List <User> users = userService.getAllUsers();
        return users;
    }


    @GetMapping("users/{id}")
    public User getUserWithID(@PathVariable(value = "id") int id) {
        return userService.getUserWithID(id);
    }

    @PostMapping(value = "users")
    public void createNewUser(@RequestBody User u) {
        System.out.println(">>>>>>>>>>>>>>>>" + u);
        userService.createNewUser(u);
    }


}
