package com.mindfire.dietplanner.core.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.dietplanner.core.entity.User;

/**
 * UserRespository acts as CRUD interface.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	// Finds user by email to check login
	public User findByEmail(String email);
	
}
