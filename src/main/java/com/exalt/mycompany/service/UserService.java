package com.exalt.mycompany.service;

import com.exalt.mycompany.dto.UserDTO;
import com.exalt.mycompany.model.User;
import com.exalt.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

import java.util.List;

/**
 * @author LaithB
 */
public interface UserService {

    /**
     * @return all users existing in the database.
     * @throws EntityNotFoundException if no users found
     */
    List<User> getAllUsers();

    /**
     *
     * @param id user id to search for
     * @return user with the specified id
     * @throws EntityNotFoundException if the user with the specified id is not found
     */
    User getUserWithID(int id);

    /**
     *
     * @param user user to create
     * @throws javax.persistence.EntityExistsException if the user with the specified email already exists
     */
    void createNewUser(User user);

    /**
     *
     * @param id user id to update its info
     * @param u info to update user
     * @return void
     * @throws EntityNotFoundException if the user with the specified id is not found
     */
    void updateUser(int id, User u);
    /**
     *
     * @param id user id to delete
     * @return void
     */
    void deleteUser(int id);

}
