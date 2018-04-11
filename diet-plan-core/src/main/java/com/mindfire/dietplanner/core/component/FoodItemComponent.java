package com.mindfire.dietplanner.core.component;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindfire.dietplanner.core.dto.FoodItemDTO;
import com.mindfire.dietplanner.core.entity.FoodItem;
import com.mindfire.dietplanner.core.repository.FoodItemRepository;

/**
 * FoodItemComponent implements set and get methods for food item entity. It is
 * also used to search food items by food item ID or by food name.
 */
@Component
public class FoodItemComponent {

	@Autowired
	FoodItemRepository foodItemRepository;

	ModelMapper mapper;
	FoodItemDTO foodItemDTO;

	public FoodItemComponent() {
		// Default constructor
		mapper = new ModelMapper();
	}

	/**
	 * Gets food item data from database using ID.
	 * 
	 * @param id
	 *            Food item ID
	 * @return {@link FoodItemDTO} Food item
	 */
	public FoodItemDTO getFoodItem(int id) {
		FoodItem foodItem = foodItemRepository.findOne(id); // Get entity
		foodItemDTO = mapper.map(foodItem, FoodItemDTO.class); // Map to DTO

		return foodItemDTO;
	}

	/**
	 * Gets food item from database using name.
	 * 
	 * @param name
	 *            Food item name
	 * @return {@link FoodItemDTO} Food item
	 */
	public FoodItemDTO getFoodItemByName(String name) {
		FoodItem foodItem = foodItemRepository.findByName(name); // Get entity
		foodItemDTO = mapper.map(foodItem, FoodItemDTO.class); // Map to DTO

		return foodItemDTO;
	}

	/**
	 * Saves food item to database.
	 * 
	 * @param foodItemDTO
	 *            Food item DTO
	 * @return {@link FoodItemDTO} Food item
	 */
	public FoodItemDTO setFoodItem(FoodItemDTO foodItemDTO) {
		FoodItem foodItem = mapper.map(foodItemDTO, FoodItem.class); // Map DTO to entity
		foodItem = foodItemRepository.save(foodItem); // Save entity

		foodItemDTO = mapper.map(foodItem, FoodItemDTO.class); // Map to DTO
		return foodItemDTO;
	}

}
