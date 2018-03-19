package com.mindfire.dietplanner.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dietplanner.api.service.DietPlannerService;
import com.mindfire.dietplanner.core.dto.UserDietDTO;

@RestController
@RequestMapping("/api/diet/plan")
@CrossOrigin
public class DietPlannerController {

	@Autowired
	DietPlannerService dietPlannerService;
	
	@GetMapping("/{id}")
	public UserDietDTO getNewDietPlan(@PathVariable int id) {
		return dietPlannerService.getNewDietPlan(id);
	}
	
}
