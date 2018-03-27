package com.mindfire.dietplanner.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dietplanner.api.service.DietPreferenceService;
import com.mindfire.dietplanner.core.dto.UserDietDTO;

/**
 * DietPreferenceController acts as an API end-point for diet preferences data.
 * This class implements mapping end-points to service calls.
 */
@RestController
@RequestMapping("/api/diet/pref")
@CrossOrigin
public class DietPreferenceController {

	@Autowired
	DietPreferenceService dietPreferenceService;

	/**
	 * Maps GET request to diet preference service with user ID, which returns a
	 * list of user's diet preferences i.e food items as user diet DTO.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link UserDietDTO} User's diet data
	 */
	@GetMapping("/{id}")
	public UserDietDTO getDietPreference(@PathVariable int id) {
		// Get food item using ID
		return dietPreferenceService.getUserDiet(id);
	}

	/**
	 * Maps POST request to diet preference service with user ID, food course and
	 * food item ID. The specified food item is added to user's preferences for the
	 * specified food course.
	 * 
	 * @param id
	 *            User ID
	 * @param foodCourse
	 *            Food course
	 * @param foodId
	 *            Food item ID
	 * @return {@link UserDietDTO} User's diet data
	 */
	@PostMapping("/{id}")
	public UserDietDTO addDietPreference(@PathVariable int id, @RequestParam int foodCourse, @RequestParam int foodId) {
		// Add food item to user's diet preferences
		return dietPreferenceService.addItemToUserDiet(id, foodCourse, foodId);
	}

	/**
	 * Maps DELETE request to diet preference service with user ID, food course and
	 * food item ID. The specified food item is removed from user's preferences
	 * based on the specified food course.
	 * 
	 * @param id
	 *            User ID
	 * @param foodCourse
	 *            Food course
	 * @param foodId
	 *            Food item ID
	 * @return {@link UserDietDTO} User's diet data
	 */
	@DeleteMapping("/{id}")
	public UserDietDTO removeDietPreference(@PathVariable int id, @RequestParam int foodCourse,
			@RequestParam int foodId) {
		// Remove food item to user's diet preferences
		return dietPreferenceService.removeItemFromUserDiet(id, foodCourse, foodId);
	}

}
