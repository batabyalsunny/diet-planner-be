package com.mindfire.dietplanner.core.dto;

public class DietPlanDTO {
	
	// Stores the nutritional details of the diet plan
	DietPlanDetailsDTO dietPlanDetailsDTO;
	// Stores the diet plan i.e all 4 courses of meal
	UserDietDTO dietPlan;
	
	// Getter and setter methods
	public DietPlanDetailsDTO getDietPlanDetails() {
		return dietPlanDetailsDTO;
	}
	public void setDietPlanDetails(DietPlanDetailsDTO dietPlanDetailsDTO) {
		this.dietPlanDetailsDTO = dietPlanDetailsDTO;
	}
	public UserDietDTO getDietPlan() {
		return dietPlan;
	}
	public void setDietPlan(UserDietDTO dietPlan) {
		this.dietPlan = dietPlan;
	}
	
}
