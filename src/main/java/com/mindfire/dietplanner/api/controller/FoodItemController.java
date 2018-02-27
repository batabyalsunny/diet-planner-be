package com.mindfire.dietplanner.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dietplanner.api.service.FoodItemService;
import com.mindfire.dietplanner.api.service.NutrientService;
import com.mindfire.dietplanner.core.dto.FoodItemDTO;
import com.mindfire.dietplanner.core.dto.NutrientDTO;

@RestController
@RequestMapping("/api/foods")
public class FoodItemController {

	@Autowired
	FoodItemService foodItemService;
	
	@Autowired
	NutrientService nutrientService;

	@GetMapping("/{id}")
	public FoodItemDTO getFoodItem(@PathVariable int id) {
		// Get food item using ID
		return foodItemService.getFoodItem(id);
	}

	@PostMapping("")
	public FoodItemDTO setFoodItem(@ModelAttribute FoodItemDTO foodItemDTO, 
	                               @ModelAttribute NutrientDTO nutrientDTO) {
		// Save food item with nutrients
		foodItemDTO.setNutrients(nutrientService.setNutrient(nutrientDTO)); // Save nutrients
		return foodItemService.setFoodItem(foodItemDTO); // Save food items
	}

}
