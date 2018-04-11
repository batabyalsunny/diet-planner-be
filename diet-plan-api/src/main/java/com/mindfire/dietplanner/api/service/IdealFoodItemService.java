package com.mindfire.dietplanner.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.core.component.IdealFoodItemComponent;
import com.mindfire.dietplanner.core.dto.IdealFoodItemDTO;

/**
 * IdealFoodItemService is a service class, used to get list of ideal food items
 * for generation of the diet plan.
 */
@Service
public class IdealFoodItemService {

	@Autowired
	IdealFoodItemComponent idealFoodItemComponent;

	/**
	 * Gets list of ideal food items for diet plan.
	 * 
	 * @return List of ideal food items
	 */
	public List<IdealFoodItemDTO> getIdealFoodItems() {
		return idealFoodItemComponent.getIdealFoodItems();
	}

	/**
	 * Adds a food item to the list of ideal food items.
	 * 
	 * @param course
	 *            Food course
	 * @param foodItemId
	 *            Food item ID
	 * @return {@link IdealFoodItemDTO} Saved ideal food item
	 */
	public IdealFoodItemDTO setIdealFoodItem(int course, int foodItemId) {
		return idealFoodItemComponent.setIdealFoodItem(course, foodItemId);
	}

}
