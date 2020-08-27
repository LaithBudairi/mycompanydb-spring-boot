package com.exalt.mycompany.controller;

import com.exalt.mycompany.dto.UserProfileDTO;
import com.exalt.mycompany.model.UserProfile;
import com.exalt.mycompany.service.UserProfileService;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "api/v1/")
@RestController
public class UserProfileController {

    private UserProfileService userProfileService;

    private final static Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    private ModelMapper userProfileMapper;

    public UserProfileController() {
        userProfileMapper = new ModelMapper();
        userProfileMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        userProfileMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Autowired
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("userprofiles/")
    public List<UserProfileDTO> getUserProfiles() {
        return userProfileService.getAllUserProfiles().stream().map(userProfile -> userProfileMapper.map(userProfile, UserProfileDTO.class)).collect(Collectors.toList());

    }

    @GetMapping("userprofiles/{id}")
    public UserProfileDTO getUserWithID(@PathVariable(value = "id") int id) {
        userProfileMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PACKAGE_PRIVATE.PRIVATE);
        return userProfileMapper.map(userProfileService.getUserProfileWithID(id), UserProfileDTO.class);

    }

    @PostMapping(value = "userprofiles/", consumes = MediaType.APPLICATION_JSON_VALUE) //Needs Fix
    public void createNewUser(@RequestBody UserProfileDTO up) {
        userProfileService.createNewUserProfile(userProfileMapper.map(up, UserProfile.class));
    }

    @PutMapping(value = "userprofiles/{id}")
    public void updateUser(@PathVariable(value = "id") int id, @RequestBody UserProfileDTO up) {
        userProfileService.updateUserProfile(id, userProfileMapper.map(up, UserProfile.class));
    }

    @DeleteMapping(value = "userprofiles/{id}")
    public void deleteUser(@PathVariable(value = "id") int id) {
        userProfileService.deleteUserProfile(id);
    }
}
