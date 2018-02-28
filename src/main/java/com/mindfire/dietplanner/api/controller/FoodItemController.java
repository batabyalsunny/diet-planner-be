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
 * FoodItemController acts as an API end-point for FoodItem data. Class
 * implements mapping end-points to service calls.
 */
@RestController
@RequestMapping("/api/foods")
@CrossOrigin
public class FoodItemController {

	@Autowired
	FoodItemService foodItemService;

	@GetMapping("/{id}")
	public FoodItemDTO getFoodItem(@PathVariable int id) {
		// Get food item using ID
		return foodItemService.getFoodItem(id);
	}
	
	@GetMapping("")
	public FoodItemDTO getFoodItemByName(@RequestParam String name) {
		// Search database with food name
		return foodItemService.getFoodItemByName(name);
	}

	@PostMapping("")
	public FoodItemDTO setFoodItem(@ModelAttribute FoodItemDTO foodItemDTO, @ModelAttribute NutrientDTO nutrientDTO) {
		// Save food item and nutrients
		return foodItemService.setFoodItem(foodItemDTO, nutrientDTO);
	}

}
