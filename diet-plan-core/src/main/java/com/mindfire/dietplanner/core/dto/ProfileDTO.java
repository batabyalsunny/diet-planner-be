package com.mindfire.dietplanner.core.dto;

import java.sql.Date;

import com.mindfire.dietplanner.core.util.AgeCaculator;
import com.mindfire.dietplanner.core.util.DietData;

/**
 * ProfileDTO class is a POJO class used to model user profile i.e details about
 * user's gender, height, weight, activity and diet.
 */
public class ProfileDTO {

	/**
	 * Attributes for profile
	 */
	int id; // Data ID

	Date dob; // Date of birth
	char gender; // Gender - M / F
	short height; // Height in CM
	short weight; // Weight in KG

	short activity; // Activity (1:No exercise - 5:Very heavy exercise)
	short diet; // Diet preference

	DietData dietData; // User's calculated diet data

	public ProfileDTO() {
		// Default Constructor
		dietData = new DietData(this);
	}

	/**
	 * Constructor initialize ProfileDTO with data i.e birth date, gender, height,
	 * weight, activity and diet preferences.
	 * 
	 * @param dob
	 *            Date of birth
	 * @param gender
	 *            Gender M/F
	 * @param height
	 *            Height in CMs
	 * @param weight
	 *            Weight in KGs
	 * @param activity
	 *            Activity scale
	 * @param diet
	 *            Diet scale
	 */
	public ProfileDTO(Date dob, char gender, short height, short weight, short activity, short diet) {
		this.dob = dob;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.activity = activity;
		this.diet = diet;

		dietData = new DietData(this);
	}

	/**
	 * Returns calculated age of user as attribute.
	 * 
	 * @return Age of the user from birth date
	 */
	public int getAge() {
		AgeCaculator calculator = new AgeCaculator();
		return calculator.getAge(dob);
	}

	/**
	 * Returns calculated BMI of user as attribute.
	 * 
	 * @return BMI Body Mass Index of user
	 */
	public float getBmi() {
		// Diet data performs diet based calculations
		return dietData.getBMIndex();
	}

	/**
	 * Returns description of user's body type based on BMI.
	 * 
	 * @return Body type description
	 */
	public String getBodyType() {
		// Gets BMI for the user
		float bmi = getBmi();
		String bodyType = "";

		// Classifies user body type
		if (bmi < 18.5)
			bodyType = "under";
		else if (bmi > 18.5 && bmi < 24.9)
			bodyType = "normal";
		else if (bmi > 25 && bmi < 29.9)
			bodyType = "over";
		else
			bodyType = "obese";

		return bodyType;
	}

	/**
	 * Returns user's BMR value as attribute.
	 * 
	 * @return BMR value
	 */
	public int getBmr() {
		// Calculate user's BMR value
		return dietData.getBMRate();
	}

	/**
	 * Returns user's daily calorie count (ideal) as attribute.
	 * 
	 * @return Daily calorie count required
	 */
	public int getDailyCalorieCount() {
		// Calculate user's daily calorie count
		return dietData.getDailyCalorie();
	}

	// Getter and Setter Methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public short getHeight() {
		return height;
	}

	public void setHeight(short height) {
		this.height = height;
	}

	public short getWeight() {
		return weight;
	}

	public void setWeight(short weight) {
		this.weight = weight;
	}

	public short getActivity() {
		return activity;
	}

	public void setActivity(short activity) {
		this.activity = activity;
	}

	public short getDiet() {
		return diet;
	}

	public void setDiet(short diet) {
		this.diet = diet;
	}

	@Override
	public String toString() {
		return "ProfileDTO [id=" + id + ", dob=" + dob + ", gender=" + gender + ", height=" + height + ", weight="
				+ weight + ", activity=" + activity + ", diet=" + diet + ", dietData=" + dietData + "]";
	}
	
}
