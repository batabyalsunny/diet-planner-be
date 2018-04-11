package com.mindfire.dietplanner.core.dto;

/**
 * FoodItemDTO is a POJO class used to model food item entity.
 */
public class FoodItemDTO {

	/**
	 * Attributes for food item
	 */
	int id; // Food item ID

	String name; // Food name
	float quantity; // Serving quantity
	String unit; // Serving unit
	float weight; // Serving weight in GMs
	String thumbUrl; // URL for thumb image
	String imageUrl; // URL for high res image

	NutrientDTO nutrientsDTO; // Food's nutrition profile (FoodItem - Nutrient)

	public FoodItemDTO() {
		// Default constructor
	}

	/**
	 * Initializes food item with data i.e name, quantity, unit, thumb
	 * and image URLs.
	 * 
	 * @param name
	 *            Name of the food item
	 * @param quantity
	 *            Serving quantity
	 * @param unit
	 *            Serving unit
	 * @param weight
	 *            Weight in GMs
	 * @param thumbUrl
	 *            Thumb image URL
	 * @param imageUrl
	 *            High resolution image URL
	 */
	public FoodItemDTO(String name, float quantity, String unit, float weight, String thumbUrl, String imageUrl) {
		this.name = name;
		this.quantity = quantity;
		this.unit = unit;
		this.weight = weight;
		this.thumbUrl = thumbUrl;
		this.imageUrl = imageUrl;
	}

	// Getter and Setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public NutrientDTO getNutrients() {
		return nutrientsDTO;
	}

	public void setNutrients(NutrientDTO nutrientsDTO) {
		this.nutrientsDTO = nutrientsDTO;
	}

	@Override
	public String toString() {
		return "FoodItemDTO [id=" + id + ", name=" + name + ", quantity=" + quantity + ", unit=" + unit + ", weight="
				+ weight + ", thumbUrl=" + thumbUrl + ", imageUrl=" + imageUrl + ", nutrientsDTO=" + nutrientsDTO + "]";
	}
	
}
