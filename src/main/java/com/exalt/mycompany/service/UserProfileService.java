package com.exalt.mycompany.service;

import com.exalt.mycompany.model.UserProfile;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author LaithB
 */
public interface UserProfileService {

    /**
     * @return all user profiles existing in the database.
     * @throws EntityNotFoundException if no user profiles found
     */
    List<UserProfile> getAllUserProfiles();
    /**
     *
     * @param id user profile id to search for
     * @return user profile with the specified id
     * @throws EntityNotFoundException if the user profile with the specified id is not found
     */
    UserProfile getUserProfileWithID(int id);
    /**
     *
     * @param up user profile to create
     */
    void createNewUserProfile(UserProfile up);
    /**
     *
     * @param id user profile id to update its info
     * @param up info to update user profile
     * @return void
     * @throws EntityNotFoundException if the user with the specified id is not found
     */
    void updateUserProfile(int id, UserProfile up);
    /**
     *
     * @param id user profile id to delete
     * @return void
     */
    void deleteUserProfile(int id);
}
