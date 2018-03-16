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

@RestController
@RequestMapping("/api/diet/pref")
@CrossOrigin
public class DietPreferenceController {

	@Autowired
	DietPreferenceService dietPreferenceService;

	@GetMapping("/{id}")
	public UserDietDTO getDietPreference(@PathVariable int id) {
		// Get food item using ID
		return dietPreferenceService.getUserDiet(id);
	}

	@PostMapping("/{id}")
	public UserDietDTO addDietPreference(@PathVariable int id, @RequestParam int foodCourse, 
											@RequestParam int foodId) {
		// Add food item to user's diet preferences
		return dietPreferenceService.addItemToUserDiet(id, foodCourse, foodId);
	}
	
	@DeleteMapping("/{id}")
	public UserDietDTO removeDietPreference(@PathVariable int id, @RequestParam int foodCourse, 
											@RequestParam int foodId) {
		// Remove food item to user's diet preferences
		return dietPreferenceService.removeItemFromUserDiet(id, foodCourse, foodId);
	}

}
