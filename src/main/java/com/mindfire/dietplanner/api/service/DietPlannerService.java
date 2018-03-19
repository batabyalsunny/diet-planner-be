package com.mindfire.dietplanner.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.core.component.DietPlannerComponent;
import com.mindfire.dietplanner.core.dto.UserDietDTO;

@Service
public class DietPlannerService {
	
	@Autowired
	DietPlannerComponent dietPlannerComponent;
	
	public UserDietDTO getNewDietPlan(int id) {
		return dietPlannerComponent.getNewDietPlan(id,1);
	}

}
