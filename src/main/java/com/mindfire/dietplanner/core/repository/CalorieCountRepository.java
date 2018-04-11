package com.mindfire.dietplanner.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.mindfire.dietplanner.core.entity.CalorieCount;

/**
 * CalorieCountRepository acts as CRUD interface.
 */
public interface CalorieCountRepository extends CrudRepository<CalorieCount, Integer> {
	
	// Find calorie counts by user ID
	public CalorieCount findByUserId(int id);
	
}
