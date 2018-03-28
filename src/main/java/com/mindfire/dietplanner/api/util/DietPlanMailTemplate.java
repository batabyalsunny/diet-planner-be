package com.mindfire.dietplanner.api.util;

import org.springframework.stereotype.Component;

import com.mindfire.dietplanner.core.dto.FoodItemDTO;
import com.mindfire.dietplanner.core.dto.UserDietDTO;

/**
 * DietPlanMailTemplate is a utility class, used to generate HTML template from
 * user's diet plan data.
 */
@Component
public class DietPlanMailTemplate {

	String message = ""; // HTML message
	String avatarClass = "border-radius: 50%; padding-right: 5px;"; // CSS class for image icon

	/**
	 * Generates a string of formatted HTML text from diet plan.
	 * 
	 * @param userDietDTO User's diet data
	 * @return String of HTML content from diet data
	 */
	public String getDietPlanHtml(UserDietDTO userDietDTO) {
		message = "";
		// Format for breakfast items
		message += "<h2>BREAKFAST</h2>";
		message += "<table>";
		userDietDTO.getBreakfast().stream().forEach(foodItem -> {
			message += "<tr>";
			message += formatFoodItem(foodItem);
			message += "</tr>";
		});
		message += "</table>";

		// Format for lunch items
		message += "<h2>LUNCH</h2>";
		message += "<table>";
		userDietDTO.getLunch().stream().forEach(foodItem -> {
			message += "<tr>";
			message += formatFoodItem(foodItem);
			message += "</tr>";
		});
		message += "</table>";

		// Format for snacks items
		message += "<h2>SNACKS</h2>";
		message += "<table>";
		userDietDTO.getSnacks().stream().forEach(foodItem -> {
			message += "<tr>";
			message += formatFoodItem(foodItem);
			message += "</tr>";
		});
		message += "</table>";

		// Format for dinner items
		message += "<h2>DINNER</h2>";
		message += "<table>";
		userDietDTO.getDinner().stream().forEach(foodItem -> {
			message += "<tr>";
			message += formatFoodItem(foodItem);
			message += "</tr>";
		});
		message += "</table>";

		return message;
	}

	/**
	 * Formats each diet item as HTML element for generating tabular view for diet
	 * plan. Adds diet item image icon, name, quantity and unit as table values.
	 * 
	 * @param {@link
	 * 			FoodItemDTO} Food item data
	 * @return {@link String} String of formatted diet item
	 */
	private String formatFoodItem(FoodItemDTO foodItem) {
		String item = "";

		/*
		 * Format for each diet item contains image icon, food item name, serving
		 * quantity and unit, as HTML table row.
		 */
		item += "<td>";
		item += "<img src='" + foodItem.getThumbUrl() + "' width='50' height='50' style='" + avatarClass + "' />";
		item += "</td>";
		item += "<td>";
		item += "<h3>" + foodItem.getName().toUpperCase() + " x " + foodItem.getQuantity() + " " + foodItem.getUnit()
				+ "</h3>";
		item += "</td>";

		return item;
	}
}
