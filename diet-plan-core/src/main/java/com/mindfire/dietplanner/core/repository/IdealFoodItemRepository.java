package com.mindfire.dietplanner.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mindfire.dietplanner.core.entity.IdealFoodItem;

/**
 * IdealFoodItemRepository acts as CRUD interface.
 */
public interface IdealFoodItemRepository extends CrudRepository<IdealFoodItem, Integer> {

	// Find ideal food item by course and food item ID
	IdealFoodItem findByCourseAndFoodItemId(int course, int foodItemId);
	
	// Find ideal food item by course
	List<IdealFoodItem> findByCourse(int course);
	
}
