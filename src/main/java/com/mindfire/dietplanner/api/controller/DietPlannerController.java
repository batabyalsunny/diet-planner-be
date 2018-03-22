package com.mindfire.dietplanner.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dietplanner.api.service.DietPlannerService;
import com.mindfire.dietplanner.api.util.MailComponent;
import com.mindfire.dietplanner.core.dto.UserDietDTO;

/**
 * DietPlannerController acts as an API end-point for generating diet plans for user.
 * Class implements mapping end-points to service calls.
 */
@RestController
@RequestMapping("/api/diet/plan")
@CrossOrigin
public class DietPlannerController {

	@Autowired
	DietPlannerService dietPlannerService;
	
	@Autowired
	MailComponent mailComponent;
	
	/**
	 * Maps GET request to diet planner service with user ID, to get suggested diet
	 * plan for the user. Diet plan is generated from the user's diet preferences
	 * and list of ideal diet food items.
	 * 
	 * @param id
	 *            User ID
	 * @return User diet DTO
	 * @throws Exception 
	 */
	@GetMapping("/{id}")
	public UserDietDTO getNewDietPlan(@PathVariable int id) throws Exception {
		return dietPlannerService.getNewDietPlan(id);
	}

}
