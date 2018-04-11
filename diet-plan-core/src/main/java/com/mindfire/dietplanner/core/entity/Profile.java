package com.mindfire.dietplanner.core.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Profile is an entity class that represents basic user profile and data i.e
 * birth date, height, weight and diet preferences.
 */
@Entity(name = "user_profiles")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; // Data ID

	private Date dob; // Date of birth
	private char gender; // Gender - M / F
	private short height; // Height in CM
	private short weight; // Weight in KG

	private short activity; // Activity (1:No exercise - 5:Very heavy exercise)
	private short diet; // Diet preference

	public Profile() {
		// Default Constructor
	}

	/**
	 * Constructor initialize user's personal data i.e birth date, gender, height,
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
	public Profile(Date dob, char gender, short height, short weight, short activity, short diet) {
		this.dob = dob;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.activity = activity;
		this.diet = diet;
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
		return "Profile [id=" + id + ", dob=" + dob + ", gender=" + gender + ", height=" + height + ", weight=" + weight
				+ ", activity=" + activity + ", diet=" + diet + "]";
	}
}
