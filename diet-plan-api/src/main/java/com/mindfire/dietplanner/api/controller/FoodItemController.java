package com.mindfire.dietplanner.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dietplanner.api.service.FoodItemService;
import com.mindfire.dietplanner.core.dto.FoodItemDTO;
import com.mindfire.dietplanner.core.dto.NutrientDTO;

/**
 * FoodItemController acts as an API end-point for food item data. This class
 * implements mapping end-points to service calls.
 */
@RestController
@RequestMapping("/api/foods")
@CrossOrigin
public class FoodItemController {

	@Autowired
	FoodItemService foodItemService;

	/**
	 * Maps GET request for food item service with food item ID, which is used to
	 * fetch a food item by a specific ID.
	 * 
	 * @param id
	 *            Food item ID
	 * @return {@link FoodItemDTO} Food item data
	 */
	@GetMapping("/{id}")
	public FoodItemDTO getFoodItem(@PathVariable int id) {
		// Get food item using ID
		return foodItemService.getFoodItem(id);
	}

	/**
	 * Maps GET request for food item service with a food item name, which is used
	 * to fetch a food item by name.
	 * 
	 * @param name
	 *            Food item name
	 * @return {@link FoodItemDTO} Food item data
	 */
	@GetMapping("")
	public FoodItemDTO getFoodItemByName(@RequestParam String name) {
		// Search database with food name
		return foodItemService.getFoodItemByName(name);
	}

	/**
	 * Maps POST request for food item service with model food item and model
	 * nutrients data. New food item is created with these model data. New nutrients
	 * data is also created for the food item with the nutrients model data.
	 * 
	 * @param foodItemDTO
	 *            Food item model
	 * @param nutrientDTO
	 *            Nutrients model
	 * @return {@link FoodItemDTO} Saved food item data
	 */
	@PostMapping("")
	public FoodItemDTO setFoodItem(@ModelAttribute FoodItemDTO foodItemDTO, @ModelAttribute NutrientDTO nutrientDTO) {
		// Save food item and nutrients
		return foodItemService.setFoodItem(foodItemDTO, nutrientDTO);
	}

}
