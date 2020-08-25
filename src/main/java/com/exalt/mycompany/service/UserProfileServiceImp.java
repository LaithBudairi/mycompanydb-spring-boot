package com.exalt.mycompany.service;

import com.exalt.mycompany.model.UserProfile;
import com.exalt.mycompany.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserProfileServiceImp implements UserProfileService{
    private UserProfileRepository userProfileRepository;

    @Autowired
    public void setUserRepository(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public List<UserProfile> getAllUserProfiles() {
        return (List<UserProfile>) userProfileRepository.findAll();
    }

    @Override
    public UserProfile getUserProfileWithID(int id) {
        return userProfileRepository.findById(id).get();
    }

    @Override
    public void createNewUserProfile(UserProfile up) {
        userProfileRepository.save(up);
    }
}
