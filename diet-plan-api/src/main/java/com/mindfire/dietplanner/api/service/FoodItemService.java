package com.mindfire.dietplanner.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.core.component.FoodItemComponent;
import com.mindfire.dietplanner.core.component.NutrientComponent;
import com.mindfire.dietplanner.core.dto.FoodItemDTO;
import com.mindfire.dietplanner.core.dto.NutrientDTO;

/**
 * FoodItemService is a service class used to add, remove and search food items.
 */
@Service
public class FoodItemService {

	@Autowired
	FoodItemComponent foodItemComponent;

	@Autowired
	NutrientComponent nutrientComponent;

	FoodItemService() {
		// Default constructor
	}

	/**
	 * Gets a food item data by ID.
	 * 
	 * @param id
	 *            Food item ID
	 * @return {@link FoodItemDTO} Food item DTO
	 */
	public FoodItemDTO getFoodItem(int id) {
		return foodItemComponent.getFoodItem(id);
	}

	/**
	 * Gets a food item data by name.
	 * 
	 * @param name
	 *            Food item name
	 * @return {@link FoodItemDTO} Food item DTO
	 */
	public FoodItemDTO getFoodItemByName(String name) {
		return foodItemComponent.getFoodItemByName(name);
	}

	/**
	 * Saves a new food item.
	 * 
	 * @param foodItemDTO
	 *            Food item details
	 * @param nutrientDTO
	 *            Food item's nutrition details
	 * @return {@link FoodItemDTO} Food item DTO
	 */
	public FoodItemDTO setFoodItem(FoodItemDTO foodItemDTO, NutrientDTO nutrientDTO) {
		// First save nutrients
		foodItemDTO.setNutrients(nutrientComponent.setNutrient(nutrientDTO));
		// Now save the food item
		return foodItemComponent.setFoodItem(foodItemDTO);
	}
}
