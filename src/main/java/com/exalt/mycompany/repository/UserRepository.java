package com.exalt.mycompany.repository;

import com.exalt.mycompany.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
