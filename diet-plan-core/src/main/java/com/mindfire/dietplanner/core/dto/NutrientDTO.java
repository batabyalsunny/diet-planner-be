package com.mindfire.dietplanner.core.dto;

/**
 * NutrientDTO is a POJO class used to model nutrition data related to a food
 * item. It contains amount of calories, fats, protein, etc. the food item
 * contains.
 */
public class NutrientDTO {

	int id; // Nutrient ID

	float calories; // Amount of calories
	float fat; // Total amount of fats
	float satFat; // Saturated fats
	float cholestrol; // Amount of cholestrol
	float sodium; // Total Sodium content
	float carbohydrates; // Amount of carbohydrates
	float protein; // Protein content
	float potassium; // Amount of Potassium

	public NutrientDTO() {
		// Default constructor
	}

	/**
	 * Initializes nutrient with required values i.e the amounts of calories, fats,
	 * protein and other such nutritional values.
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
	public NutrientDTO(float calories, float fat, float satFat, float cholestrol, float sodium, float carbohydrates,
			float protein, float potassium) {
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

	@Override
	public String toString() {
		return "NutrientDTO [id=" + id + ", calories=" + calories + ", fat=" + fat + ", satFat=" + satFat
				+ ", cholestrol=" + cholestrol + ", sodium=" + sodium + ", carbohydrates=" + carbohydrates
				+ ", protein=" + protein + ", potassium=" + potassium + "]";
	}

}
