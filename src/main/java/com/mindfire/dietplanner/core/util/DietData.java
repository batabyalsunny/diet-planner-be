package com.mindfire.dietplanner.core.util;

import com.mindfire.dietplanner.core.dto.ProfileDTO;

/**
 * DietData class gets user profile and calculate diet metrics such as BMI, BMR
 * and required amount of KCAL/DAY based on BMR.
 */
public class DietData {

	ProfileDTO profile; // Height, weight, activity data

	/**
	 * Constructor for DietData takes instance of the Profile.
	 * 
	 * @param {{@link
	 * 			ProfileDTO}} User's diet profile
	 */
	public DietData(ProfileDTO profile) {
		this.profile = profile;
	}

	/**
	 * Returns user's BMI i.e Body Mass Index based on height and weight values.
	 * 
	 * @return BMI value
	 */
	public float getBMIndex() {
		// Convert height and weight to required units
		int weight = profile.getWeight(); // Weight in KG already
		float height = (float) (profile.getHeight() * 0.01); // User height in M

		// BMI = weight / height^2
		return (weight / (height * height));
	}

	/**
	 * Returns user's calculated BMR using Mifflin - St. Joer Equation.
	 * 
	 * @return BMR value
	 */
	public int getBMRate() {

		// Collect required user data
		char gender = profile.getGender(); // M/F
		short height = profile.getHeight(); // Height in CM
		short weight = profile.getWeight(); // Weight in KG
		int age = profile.getAge(); // Age of user

		// Calculate BMR i.e Basal Metabolic Rate
		// Mifflin - St. Joer Equation approved by US Government
		float bmr = (float) ((10 * weight) + (6.25 * height) - (5 * age));

		// Gender based adjustments
		if (gender == 'M' || gender == 'm') {
			bmr = bmr + 5; // Male
		} else if (gender == 'F' || gender == 'f') {
			bmr = bmr - 161; // Female
		} else {
			bmr = bmr + 2; // Invalid
		}

		return (int) bmr;
	}

	/**
	 * Returns user's daily ideal calorie intakes based on BMR and Harris-Bendedict
	 * Principle of classification of diet based on activity scale.
	 * 
	 * @return Daily calorie count
	 */
	public int getDailyCalorie() {
		int calories = 0; // Daily calorie intakes required
		int activity = profile.getActivity(); // Scale: 1 - 5 (None-Heavy)
		int bmr = getBMRate(); // BMR value

		// Calculate daily calorie intakes required based on activity
		// Harris - Benedict Principle
		switch (activity) {
		case 1:
			// No exercise
			calories = (int) (bmr * 1.2);
			break;
		case 2:
			// Little exercise
			calories = (int) (bmr * 1.375);
			break;
		case 3:
			// Moderate exercise
			calories = (int) (bmr * 1.55);
			break;
		case 4:
			// Heavy exercise
			calories = (int) (bmr * 1.725);
			break;
		case 5:
			// Very heavy exercise
			calories = (int) (bmr * 1.9);
			break;
		default:
			calories = 0; // Invalid
		}

		return calories;
	}

}
