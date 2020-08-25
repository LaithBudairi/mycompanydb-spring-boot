package com.exalt.mycompany.service;

import com.exalt.mycompany.model.UserProfile;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> getAllUserProfiles();
    UserProfile getUserProfileWithID(int id);
    void createNewUserProfile(UserProfile up);
}
