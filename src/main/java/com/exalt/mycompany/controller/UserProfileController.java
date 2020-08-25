package com.exalt.mycompany.controller;

import com.exalt.mycompany.model.User;
import com.exalt.mycompany.model.UserProfile;
import com.exalt.mycompany.service.UserProfileService;
import com.exalt.mycompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "api/v1/")
@RestController
public class UserProfileController {
    private UserProfileService userProfileService;

    @Autowired
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("userprofiles/")
    public List<UserProfile> getUsers() {
        return userProfileService.getAllUserProfiles();
    }

    @GetMapping("userprofiles/{id}")
    public UserProfile getUserWithID(@PathVariable(value = "id") int id) {
        return userProfileService.getUserProfileWithID(id);
    }

    @PostMapping(value = "userprofiles/adduserprofile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNewUser(@RequestBody UserProfile up) {
        System.out.println(">>>>>>>>>>>>>>>>" + up);
        userProfileService.createNewUserProfile(up);
    }
}
