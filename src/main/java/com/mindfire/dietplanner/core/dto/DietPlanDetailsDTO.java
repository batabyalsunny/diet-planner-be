package com.mindfire.dietplanner.core.dto;

public class DietPlanDetailsDTO {

	int idealCalories;
	int totalCaloires;
	
	int idealCarbs;
	int totalCarbs;
	
	int idealProtein;
	int totalProtein;
	
	int idealFats;
	int totalFats;
	
	// Default constructor
	public DietPlanDetailsDTO() { }
	
	// Getter and setter methods
	public int getIdealCalories() {
		return idealCalories;
	}

	public void setIdealCalories(int idealCalories) {
		this.idealCalories = idealCalories;
	}

	public int getTotalCaloires() {
		return totalCaloires;
	}

	public void setTotalCaloires(int totalCaloires) {
		this.totalCaloires = totalCaloires;
	}

	public int getIdealCarbs() {
		return idealCarbs;
	}

	public void setIdealCarbs(int idealCarbs) {
		this.idealCarbs = idealCarbs;
	}

	public int getTotalCarbs() {
		return totalCarbs;
	}

	public void setTotalCarbs(int totalCarbs) {
		this.totalCarbs = totalCarbs;
	}

	public int getIdealProtein() {
		return idealProtein;
	}

	public void setIdealProtein(int idealProtein) {
		this.idealProtein = idealProtein;
	}

	public int getTotalProtein() {
		return totalProtein;
	}

	public void setTotalProtein(int totalProtein) {
		this.totalProtein = totalProtein;
	}

	public int getIdealFats() {
		return idealFats;
	}

	public void setIdealFats(int idealFats) {
		this.idealFats = idealFats;
	}

	public int getTotalFats() {
		return totalFats;
	}

	public void setTotalFats(int totalFats) {
		this.totalFats = totalFats;
	}
	
}
