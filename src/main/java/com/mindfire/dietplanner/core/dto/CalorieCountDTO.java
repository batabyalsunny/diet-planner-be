package com.mindfire.dietplanner.core.dto;

import java.util.Arrays;

import com.mindfire.dietplanner.core.entity.User;

/**
 * CalorieCountDTO is a POJO class used to model user's calorie counts.
 */
public class CalorieCountDTO {

	int id;

	User user;

	int[] dietCounts; // Array to store no. of diets consumed
	long[] totalCalories; // Array to store total amount of calories from the diets

	/**
	 * Initializes user's recorded total amount of calories and no. of diets from
	 * which the amount of calories is derived.
	 */
	public CalorieCountDTO() {
		dietCounts = new int[12];
		totalCalories = new long[12];
	}

	// Getter and setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int[] getDietCounts() {
		return dietCounts;
	}

	public void setDietCounts(int[] dietCounts) {
		this.dietCounts = dietCounts;
	}

	public long[] getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(long[] totalCalories) {
		this.totalCalories = totalCalories;
	}

	@Override
	public String toString() {
		return "CalorieCountDTO [id=" + id + ", user=" + user + ", dietCounts=" + Arrays.toString(dietCounts)
				+ ", totalCalories=" + Arrays.toString(totalCalories) + "]";
	}
	
}
