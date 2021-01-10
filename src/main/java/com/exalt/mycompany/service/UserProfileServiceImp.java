package com.exalt.mycompany.service;

import com.exalt.mycompany.controller.DeviceController;
import com.exalt.mycompany.model.Role;
import com.exalt.mycompany.model.User;
import com.exalt.mycompany.model.UserProfile;
import com.exalt.mycompany.repository.UserProfileRepository;
import com.exalt.mycompany.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserProfileServiceImp implements UserProfileService{
    private UserRepository userRepository;
    private UserProfileRepository userProfileRepository;
    private final static Logger logger = LoggerFactory.getLogger(UserProfileServiceImp.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserProfile> getAllUserProfiles() {
        List<UserProfile> userProfiles =  (List<UserProfile>) userProfileRepository.findAll();
        if(userProfiles.size() == 0) {
            throw new EntityNotFoundException("No User Profiles Found");

        }
        return userProfiles;
    }

    @Override
    public UserProfile getUserProfileWithID(int id) {
        return userProfileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User profile with ID={" + id + "}"));
    }

    @Override
    public void createNewUserProfile(UserProfile up) {
        logger.info("profile: " + up);
        User u = up.getUser();
        u.setEnabled(true);
        u.setPassword(passwordEncoder.encode(u.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = new Role();

        role.setName("ROLE_DIRECTOR");
        role.setId(2);
        roles.add(role);
        u.setRoles(roles);

        logger.info(up.toString());
        logger.info(u.toString());

        userProfileRepository.save(up);
    }

    @Override
    @Transactional
    public void updateUserProfile(int id, UserProfile up) {
        getUserProfileWithID(id);
        up.setId(id);
        userProfileRepository.save(up);
    }

    @Override
    public void deleteUserProfile(int id) {
        userProfileRepository.deleteById(id);
    }
}
