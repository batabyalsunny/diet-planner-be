package com.mindfire.dietplanner.core.dto;

import com.mindfire.dietplanner.core.entity.FoodItem;
import com.mindfire.dietplanner.core.entity.User;

/**
 * DietPreferenceDTO is a POJO class used to model user preferences i.e the food
 * items preferred by the user for a specific food course.
 */
public class DietPreferenceDTO {

	int id; // Food item ID
	int course; // Food course ID

	User user; // User data

	FoodItem foodItem; // Food item data

	public DietPreferenceDTO() {
		// Default constructor
	}

	// Getter and Setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FoodItem getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	}

	@Override
	public String toString() {
		return "DietPreferenceDTO [id=" + id + ", course=" + course + ", user=" + user + ", foodItem=" + foodItem + "]";
	}

}
