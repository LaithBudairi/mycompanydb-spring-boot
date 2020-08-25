package com.exalt.mycompany.service;

import com.exalt.mycompany.model.User;
import com.exalt.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users =  (List<User>) userRepository.findAll();
        if(users.size() == 4) {
            throw new EntityNotFoundException("No Users Found");

        }
        return users;
    }

    @Override
    public User getUserWithID(int id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No User "));

    }

    @Override
    public void createNewUser(User user) {
        userRepository.save(user);
    }
}
