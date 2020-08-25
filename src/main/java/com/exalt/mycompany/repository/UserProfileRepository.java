package com.exalt.mycompany.repository;

import com.exalt.mycompany.model.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {
}
