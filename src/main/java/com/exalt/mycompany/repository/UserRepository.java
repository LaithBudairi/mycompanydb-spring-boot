package com.exalt.mycompany.repository;

import com.exalt.mycompany.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUserName(String userName);
    User findDistinctByEmail(String email);

}
