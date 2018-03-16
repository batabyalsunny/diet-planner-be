package com.mindfire.dietplanner.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.core.component.DietPreferenceComponent;
import com.mindfire.dietplanner.core.dto.UserDietDTO;

@Service
public class DietPreferenceService {

	@Autowired
	DietPreferenceComponent dietPreferenceComponent;
	
	public DietPreferenceService() {
		// Default constructor
	}
	
	public UserDietDTO getUserDiet(int id) {
		return dietPreferenceComponent.getUserDiet(id);
	}

	public UserDietDTO addItemToUserDiet(int id, int foodCourse, int foodId) {
		return dietPreferenceComponent.addItemToUserDiet(id, foodCourse, foodId);
	}
	
	public UserDietDTO removeItemFromUserDiet(int id, int foodCourse, int foodId) {
		return dietPreferenceComponent.removeItemFromUserDiet(id, foodCourse, foodId);
	}
}	
