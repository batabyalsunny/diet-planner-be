package com.mindfire.dietplanner.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * FoodItem is an entity class used to represent a daily diet food item.
 */
@Entity(name = "food_items")
public class FoodItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; // Food item ID

	@Column(unique = true)
	private String name; // Food name
	private float quantity; // Serving quantity
	private String unit; // Serving unit
	private int weight; // Serving weight in GMs
	private String thumbUrl; // URL for thumb image
	private String imageUrl; // URL for high res image

	@OneToOne(targetEntity = Nutrient.class)
	Nutrient nutrient; // Food's nutrition profile (FoodItem -- Nutrient)

	public FoodItem() {
		// Default constructor
	}

	/**
	 * Initializes a food item with data such as item name, serving quantity, unit,
	 * weight per unit and image URLs.
	 * 
	 * @param name Food item name
	 * @param quantity Serving quantity
	 * @param unit Serving unit
	 * @param weight Weight per unit
	 * @param thumbUrl Thumb image URL
	 * @param imageUrl Full image URL
	 */
	public FoodItem(String name, float quantity, String unit, int weight, String thumbUrl, String imageUrl) {
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
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

	public Nutrient getNutrients() {
		return nutrient;
	}

	public void setNutrients(Nutrient nutrient) {
		this.nutrient = nutrient;
	}

}
