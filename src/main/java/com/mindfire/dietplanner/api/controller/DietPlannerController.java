package com.mindfire.dietplanner.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dietplanner.api.service.DietPlannerService;
import com.mindfire.dietplanner.core.dto.CalorieCountDTO;
import com.mindfire.dietplanner.core.dto.CalorieTrackDTO;
import com.mindfire.dietplanner.core.dto.UserDietDTO;

/**
 * DietPlannerController acts as an API end-point for generating diet plans for
 * user. This class implements mapping end-points to service calls.
 */
@RestController
@RequestMapping("/api/diet/plan")
@CrossOrigin
public class DietPlannerController {

	@Autowired
	DietPlannerService dietPlannerService;

	/**
	 * Maps GET request to diet planner service with user ID, to get suggested diet
	 * plan for the user. Diet plan is generated from the user's diet preferences
	 * and list of ideal diet food items.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link UserDietDTO} New diet plan for user
	 */
	@GetMapping("/{id}")
	public UserDietDTO getNewDietPlan(@PathVariable int id) {
		return dietPlannerService.getNewDietPlan(id);
	}

	/**
	 * Maps GET request to diet planner service with user ID, to get the current
	 * diet plan for the user. The current diet plan in memory is use to record
	 * user's calorie intakes.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link UserDietDTO} User's current diet plan
	 */
	@GetMapping("/mail/{id}")
	public UserDietDTO getCurrentDietPlan(@PathVariable int id) {
		return dietPlannerService.getCurrentDietPlan(id);
	}

	/**
	 * Maps GET request to diet planner service with user ID, to record the current
	 * diet plan for user and keep track of user's monthly calorie intakes.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link CalorieCountDTO} Calorie counts of user for the current year
	 */
	@GetMapping("/record/{id}")
	public CalorieCountDTO recordCurrentDietPlan(@PathVariable int id) {
		return dietPlannerService.recordDietPlan(id);
	}

	/**
	 * Maps GET request to diet planner service with user ID, to fetch the user's
	 * calorie track for the current year. Calorie track is the monthly track of
	 * user's average monthly calorie intakes.
	 * 
	 * @param id
	 *            User ID
	 * @return {@link CalorieTrackDTO} User's calorie track
	 */
	@GetMapping("/track/{id}")
	public CalorieTrackDTO trackCalorieCount(@PathVariable int id) {
		return dietPlannerService.trackCalorieCount(id);
	}

}
