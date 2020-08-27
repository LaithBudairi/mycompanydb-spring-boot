package com.exalt.mycompany.controller;

import com.exalt.mycompany.dto.UserDTO;
import com.exalt.mycompany.model.User;
import com.exalt.mycompany.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "api/v1/")
@RestController
public class UserController {

    private UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private ModelMapper userMapper;

    public UserController() {
        userMapper = new ModelMapper();
        userMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        userMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public List<UserDTO> getUsers() {
        return userService.getAllUsers().stream().map(user -> userMapper.map(user, UserDTO.class)).collect(Collectors.toList());

    }

    @GetMapping("users/{id}")
    public UserDTO getUserWithID(@PathVariable(value = "id") int id) {
        return userMapper.map(userService.getUserWithID(id), UserDTO.class);
    }

    @PostMapping(value = "users")
    public void createNewUser(@RequestBody UserDTO u) {
        userService.createNewUser(userMapper.map(u, User.class));
    }

    @PutMapping(value = "users/{id}")
    public void updateUser(@PathVariable(value = "id") int id, @RequestBody UserDTO u) {
        userService.updateUser(id, userMapper.map(u, User.class));
    }

    @DeleteMapping(value = "users/{id}")
    public void deleteUser(@PathVariable(value = "id") int id) {
        userService.deleteUser(id);
    }


}
