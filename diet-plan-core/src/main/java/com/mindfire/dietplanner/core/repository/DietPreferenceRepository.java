package com.mindfire.dietplanner.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mindfire.dietplanner.core.entity.DietPreference;

/**
 * DietPreferenceRepository acts as CRUD interface.
 */
public interface DietPreferenceRepository extends CrudRepository<DietPreference, Integer> {

	// Find all diet preferences for user by ID
	public List<DietPreference> findByUserId(int id);
	
	// Find diet preference specific for a user ID with food item and course
	public DietPreference findByUserIdAndFoodItemIdAndCourse(int id, int foodItemId, int course);
	
}
