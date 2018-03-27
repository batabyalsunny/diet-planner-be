package com.mindfire.dietplanner.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dietplanner.api.service.IdealFoodItemService;
import com.mindfire.dietplanner.core.dto.IdealFoodItemDTO;

/**
 * IdealFoodItemController acts as an API end-point for ideal food items i.e
 * food items that are preferred for a healthy diet. This class implements
 * mapping end-points to service calls.
 */
@RestController
@RequestMapping("/api/foods/ideal")
@CrossOrigin
public class IdealFoodItemController {

	@Autowired
	IdealFoodItemService idealFoodItemService;

	/**
	 * Maps GET request for ideal food item service, used to get the list of all
	 * ideal food items with sub-list according to meal courses. It contains
	 * sub-lists of food ideal for breakfast, lunch, snacks and dinner.
	 * 
	 * @return {@link List<IdealFoodItemDTO>} List of ideal food items
	 */
	@GetMapping("")
	public List<IdealFoodItemDTO> getIdealFoodItems() {
		return idealFoodItemService.getIdealFoodItems();
	}

	/**
	 * Maps POST request for ideal food item service, used to add ideal food items
	 * according to the preferred meal courses.
	 * 
	 * @param course Food course
	 * @param foodItemId Food item ID
	 * @return {@link IdealFoodItemDTO} Ideal food item DTO
	 */
	@PostMapping("")
	public IdealFoodItemDTO setIdealFoodItem(@RequestParam int course, @RequestParam int foodItemId) {
		return idealFoodItemService.setIdealFoodItem(course, foodItemId);
	}

}
