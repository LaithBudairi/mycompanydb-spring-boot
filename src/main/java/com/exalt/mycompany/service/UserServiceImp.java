package com.exalt.mycompany.service;

import com.exalt.mycompany.model.User;
import com.exalt.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users =  (List<User>) userRepository.findAll();
        if(users.size() == 0) {
            throw new EntityNotFoundException("No Users Found");

        }
        return users;
    }

    @Override
    public User getUserWithID(int id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No User With ID={" + id + "} Was Found"));

    }

    @Override
    public void createNewUser(User user) {
        User u = userRepository.findDistinctByEmail(user.getEmail());

        if(u == null) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            System.out.println("Password: " + user.getPassword());
            userRepository.save(user);
        }
        else
            throw new EntityExistsException("Duplicate User With Email={" + user.getEmail() + "}");
    }

    @Override
    @Transactional
    public void updateUser(int id, User u) {
        getUserWithID(id);
        u.setId(id);
        userRepository.save(u);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
