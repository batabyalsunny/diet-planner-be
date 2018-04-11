package com.mindfire.dietplanner.core.dto;

import java.util.Arrays;

/**
 * CalorieTrackDTO is a POJO class used to model user's calorie track i.e
 * average monthly counts of calorie intakes per day as per recorded by the user
 * from the generated diet plans.
 */
public class CalorieTrackDTO {

	int[] monthlyAverageCalorieCount; // Array to store monthly average calorie counts

	/**
	 * Initializes monthly average calorie counts for the user.
	 */
	public CalorieTrackDTO() {
		monthlyAverageCalorieCount = new int[12];
	}

	// Getter and setter methods
	public int[] getMonthlyAverageCalorieCount() {
		return monthlyAverageCalorieCount;
	}

	public void setMonthlyAverageCalorieCount(int[] monthlyAverageCalorieCount) {
		this.monthlyAverageCalorieCount = monthlyAverageCalorieCount;
	}

	@Override
	public String toString() {
		return "CalorieTrackDTO [monthlyAverageCalorieCount=" + Arrays.toString(monthlyAverageCalorieCount) + "]";
	}

}
