package com.mindfire.dietplanner.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * CalorieCount is an entity class used to represent user's monthly calorie
 * counts for the current year.
 */
@Entity(name = "calorie_count")
public class CalorieCount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	@OneToOne
	User user; // CalorieCount -- User (One - One)

	int[] dietCounts;
	long[] totalCalories;

	public CalorieCount() {
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

}
