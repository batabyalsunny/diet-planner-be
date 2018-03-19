package com.mindfire.dietplanner.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.core.component.IdealFoodItemComponent;
import com.mindfire.dietplanner.core.dto.IdealFoodItemDTO;

@Service
public class IdealFoodItemService {

	@Autowired
	IdealFoodItemComponent idealFoodItemComponent;
	
	public List<IdealFoodItemDTO> getIdealFoodItems() {
		return idealFoodItemComponent.getIdealFoodItems();
	}
	
	public IdealFoodItemDTO setIdealFoodItem(int course, int foodItemId) {
		return idealFoodItemComponent.setIdealFoodItem(course, foodItemId);
	}
	
}
