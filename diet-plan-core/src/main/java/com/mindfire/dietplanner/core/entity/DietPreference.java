package com.mindfire.dietplanner.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * DietPreference is an entity class that is used to represent user's diet
 * preferences i.e food items user prefer for a certain food course.
 */
@Entity(name = "diet_preferences")
public class DietPreference {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	int course;

	@OneToOne
	User user; // DietPreference -- User (One - One)

	@OneToOne 
	FoodItem foodItem; // DietPreference -- FoodItem (One - One)

	public DietPreference() {
		// Default constructor
	}

	// Getter and Setter methods
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

}
