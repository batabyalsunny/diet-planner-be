package com.mindfire.dietplanner.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.dietplanner.api.service.IdealFoodItemService;
import com.mindfire.dietplanner.core.dto.IdealFoodItemDTO;

@RestController
@RequestMapping("/api/foods/ideal")
@CrossOrigin
public class IdealFoodItemController {
	
	@Autowired
	IdealFoodItemService idealFoodItemService;
	
	@GetMapping("")
	public List<IdealFoodItemDTO> getIdealFoodItems() {
		return idealFoodItemService.getIdealFoodItems();
	}

	@PostMapping("")
	public IdealFoodItemDTO setIdealFoodItem(@RequestParam int course, @RequestParam int foodItemId) {
		return idealFoodItemService.setIdealFoodItem(course, foodItemId);
	}
	
}
