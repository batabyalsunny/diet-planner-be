package com.mindfire.dietplanner.core.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDietDTO acts as a custom DTO to map lists of food items sorted according
 * to food courses of user's preference. It contains list of foods items user
 * prefer as breakfast, lunch, snacks, dinner and extra stuff such as fast foods.
 */
public class UserDietDTO {

	List<FoodItemDTO> breakfast; // Course = 1
	List<FoodItemDTO> lunch; // Course = 2
	List<FoodItemDTO> snacks; // Course = 3
	List<FoodItemDTO> dinner; // Course = 4
	List<FoodItemDTO> extras; // Course = 5

	public UserDietDTO() {
		// Default constructor
		breakfast = new ArrayList<>();
		lunch = new ArrayList<>();
		snacks = new ArrayList<>();
		dinner = new ArrayList<>();
		extras = new ArrayList<>();
	}

	// Food items adding methods for various courses
	public void addBreakfastItem(FoodItemDTO item) {
		breakfast.add(item);
	}

	public void addLunchItem(FoodItemDTO item) {
		lunch.add(item);
	}

	public void addSnacksItem(FoodItemDTO item) {
		snacks.add(item);
	}

	public void addDinnerItem(FoodItemDTO item) {
		dinner.add(item);
	}

	public void addExtraItem(FoodItemDTO item) {
		extras.add(item);
	}

	// Getter and Setter methods
	public List<FoodItemDTO> getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(List<FoodItemDTO> breakfast) {
		this.breakfast = breakfast;
	}

	public List<FoodItemDTO> getLunch() {
		return lunch;
	}

	public void setLunch(List<FoodItemDTO> lunch) {
		this.lunch = lunch;
	}

	public List<FoodItemDTO> getSnacks() {
		return snacks;
	}

	public void setSnacks(List<FoodItemDTO> snacks) {
		this.snacks = snacks;
	}

	public List<FoodItemDTO> getDinner() {
		return dinner;
	}

	public void setDinner(List<FoodItemDTO> dinner) {
		this.dinner = dinner;
	}

	public List<FoodItemDTO> getExtras() {
		return extras;
	}

	public void setExtras(List<FoodItemDTO> extras) {
		this.extras = extras;
	}

	@Override
	public String toString() {
		return "UserDietDTO [breakfast=" + breakfast + ", lunch=" + lunch + ", snacks=" + snacks + ", dinner=" + dinner
				+ ", extras=" + extras + "]";
	}

}
