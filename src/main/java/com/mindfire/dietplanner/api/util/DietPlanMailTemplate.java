package com.mindfire.dietplanner.api.util;

import org.springframework.stereotype.Component;

import com.mindfire.dietplanner.core.dto.FoodItemDTO;
import com.mindfire.dietplanner.core.dto.UserDietDTO;

@Component
public class DietPlanMailTemplate {

	String message = "";

	String avatarClass = "border-radius: 50%; padding-right: 5px;";

	public String getDietPlanHtml(UserDietDTO userDietDTO) {
		message = "";
		message += "<h2>BREAKFAST</h2>";
		message += "<table>";
		userDietDTO.getBreakfast().stream().forEach(foodItem -> {
			message += "<tr>";
			message += formatFoodItem(foodItem);
			message += "</tr>";
		});
		message += "</table>";

		message += "<h2>LUNCH</h2>";
		message += "<table>";
		userDietDTO.getLunch().stream().forEach(foodItem -> {
			message += "<tr>";
			message += formatFoodItem(foodItem);
			message += "</tr>";
		});
		message += "</table>";

		message += "<h2>SNACKS</h2>";
		message += "<table>";
		userDietDTO.getSnacks().stream().forEach(foodItem -> {
			message += "<tr>";
			message += formatFoodItem(foodItem);
			message += "</tr>";
		});
		message += "</table>";

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

	private String formatFoodItem(FoodItemDTO foodItem) {
		String item = "";

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
