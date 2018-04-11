package com.mindfire.dietplanner.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Nutrient class is an entity class used to represent nutritional values
 * associated with a food item.
 */
@Entity(name = "nutrients")
public class Nutrient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private float calories; // Amount of calories
	private float fat; // Total amount of fats
	private float satFat; // Saturated fats
	private float cholestrol; // Amount of Cholestrol
	private float sodium; // Total Sodium content
	private float carbohydrates; // Amount of carbohydrates
	private float protein; // Protein content
	private float potassium; // Amount of Potassium

	public Nutrient() {
		// Default constructor
	}

	/**
	 * Initializes nutritional values such as amount of calories, fats, protein,
	 * etc.
	 * 
	 * @param calories
	 *            Amount of calories
	 * @param fat
	 *            Amount of fats
	 * @param satFat
	 *            Amount of saturated fats
	 * @param cholestrol
	 *            Amount of cholestrol
	 * @param sodium
	 *            Amount of sodium
	 * @param carbohydrates
	 *            Amount of carbohydrates
	 * @param protein
	 *            Amount of protein
	 * @param potassium
	 *            Amount of potassium
	 */
	public Nutrient(float calories, float fat, float satFat, float cholestrol, float sodium, float carbohydrates,
			float protein, float potassium) {
		super();
		this.calories = calories;
		this.fat = fat;
		this.satFat = satFat;
		this.cholestrol = cholestrol;
		this.sodium = sodium;
		this.carbohydrates = carbohydrates;
		this.protein = protein;
		this.potassium = potassium;
	}

	// Getter and Setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getCalories() {
		return calories;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}

	public float getFat() {
		return fat;
	}

	public void setFat(float fat) {
		this.fat = fat;
	}

	public float getSatFat() {
		return satFat;
	}

	public void setSatFat(float satFat) {
		this.satFat = satFat;
	}

	public float getCholestrol() {
		return cholestrol;
	}

	public void setCholestrol(float cholestrol) {
		this.cholestrol = cholestrol;
	}

	public float getSodium() {
		return sodium;
	}

	public void setSodium(float sodium) {
		this.sodium = sodium;
	}

	public float getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(float carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public float getProtein() {
		return protein;
	}

	public void setProtein(float protein) {
		this.protein = protein;
	}

	public float getPotassium() {
		return potassium;
	}

	public void setPotassium(float potassium) {
		this.potassium = potassium;
	}

}
