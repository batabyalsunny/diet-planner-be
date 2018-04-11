package com.mindfire.dietplanner.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.dietplanner.core.entity.Profile;

/**
 * UserDataRepository acts as a CRUD interface.
 */
@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer> {
	
}
