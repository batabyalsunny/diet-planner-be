package com.mindfire.dietplanner.core.dto;

/**
 * IdealFoodItemDTO is a POJO class used to model ideal food items i.e
 * suggestions for ideal diet plan.
 */
public class IdealFoodItemDTO {

	int id; // Ideal food item ID
	int course; // Food course the item is ideal for
	FoodItemDTO foodItemDTO; // Food item

	// Getter and setter methods
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

	public FoodItemDTO getFoodItem() {
		return foodItemDTO;
	}

	public void setFoodItem(FoodItemDTO foodItem) {
		this.foodItemDTO = foodItem;
	}

	@Override
	public String toString() {
		return "IdealFoodItemDTO [id=" + id + ", course=" + course + ", foodItemDTO=" + foodItemDTO + "]";
	}
	
}
