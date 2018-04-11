package com.mindfire.dietplanner.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * IdealFoodItem is an entity class used to represent an ideal food item i.e.
 * suggested for an ideal diet plan for a specified food course.
 */
@Entity(name = "ideal_food_items")
public class IdealFoodItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	int course;

	@OneToOne
	FoodItem foodItem; // IdealFoodItem -- FoodItem (One - One)

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

	public FoodItem getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	}

}
