package com.mindfire.dietplanner.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.dietplanner.core.entity.FoodItem;

/**
 * FoodItemRepository acts as a CRUD interface.
 */
@Repository
public interface FoodItemRepository extends CrudRepository<FoodItem, Integer> {
	
	// Search food item by name
	FoodItem findByName(String name);
	
}
