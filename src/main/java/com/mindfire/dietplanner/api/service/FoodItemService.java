package com.mindfire.dietplanner.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.core.component.FoodItemComponent;
import com.mindfire.dietplanner.core.dto.FoodItemDTO;

/**
 * FoodItemService implements the business logic for FoodItem. It also
 * implements basic CRUD implementations.
 */
@Service
public class FoodItemService {

	@Autowired
	FoodItemComponent foodItemComponent;

	FoodItemService() {
		// Default constructor
	}

	/**
	 * Gets a food item data by ID.
	 * 
	 * @param id
	 *            Food item ID
	 * @return Food item
	 */
	public FoodItemDTO getFoodItem(int id) {
		return foodItemComponent.getFoodItem(id);
	}

	/**
	 * Saves a new food item.
	 * 
	 * @param newFoodItemDTO
	 *            Instance of new food item
	 * @return Saved food item
	 */
	public FoodItemDTO setFoodItem(FoodItemDTO newFoodItemDTO) {
		return foodItemComponent.setFoodItem(newFoodItemDTO);
	}
}
