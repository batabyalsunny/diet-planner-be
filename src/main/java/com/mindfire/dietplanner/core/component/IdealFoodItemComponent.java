package com.mindfire.dietplanner.core.component;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindfire.dietplanner.core.dto.FoodItemDTO;
import com.mindfire.dietplanner.core.dto.IdealFoodItemDTO;
import com.mindfire.dietplanner.core.entity.FoodItem;
import com.mindfire.dietplanner.core.entity.IdealFoodItem;
import com.mindfire.dietplanner.core.repository.FoodItemRepository;
import com.mindfire.dietplanner.core.repository.IdealFoodItemRepository;

/**
 * IdealFoodItemComponent class implements get and set methods for ideal food
 * items entity i.e suggestions for diet plans. It is basically used to get list
 * of food item suggestions for specific food courses such as breakfast, lunch,
 * etc.
 */
@Component
public class IdealFoodItemComponent {

	@Autowired
	IdealFoodItemRepository idealFoodItemRepository;
	@Autowired
	FoodItemRepository foodItemRepository;

	ModelMapper mapper;

	public IdealFoodItemComponent() {
		// Default constructor
		mapper = new ModelMapper();
	}

	/**
	 * Gets list of all ideal food items.
	 * 
	 * @return {@link List<IdealFoodItemDTO>} List of ideal food items
	 */
	public List<IdealFoodItemDTO> getIdealFoodItems() {
		List<IdealFoodItem> idealFoodItems = (List<IdealFoodItem>) idealFoodItemRepository.findAll();
		List<IdealFoodItemDTO> idealFoodItemDTOs = new ArrayList<>();

		idealFoodItems.stream().forEach(idealFoodIteam -> {
			IdealFoodItemDTO idealFoodItemDTO = mapper.map(idealFoodIteam, IdealFoodItemDTO.class);
			idealFoodItemDTOs.add(idealFoodItemDTO);
		});

		return idealFoodItemDTOs;
	}

	/**
	 * Adds a food item to specified food course as an ideal food item for that
	 * course.
	 * 
	 * @param course
	 *            Food course
	 * @param foodItemId
	 *            Food item ID
	 * @return {@link IdealFoodItemDTO} Ideal food item DTO
	 */
	public IdealFoodItemDTO setIdealFoodItem(int course, int foodItemId) {
		FoodItem foodItem = foodItemRepository.findOne(foodItemId); // Get stored food item by ID

		IdealFoodItem idealFoodItem = idealFoodItemRepository.findByCourseAndFoodItemId(course, foodItemId);

		// Ideal food item already exists, no need to save
		if (idealFoodItem != null) {
			return mapper.map(idealFoodItem, IdealFoodItemDTO.class);
		}

		// Create new ideal food item
		idealFoodItem = new IdealFoodItem();
		idealFoodItem.setCourse(course);
		idealFoodItem.setFoodItem(foodItem);

		// Save and return DTO
		idealFoodItem = idealFoodItemRepository.save(idealFoodItem);
		return mapper.map(idealFoodItem, IdealFoodItemDTO.class);
	}

	/**
	 * Functions return list of ideal food items suggested for breakfast.
	 * 
	 * @return {@link List<FoodItemDTO>} List of food items
	 */
	public List<FoodItemDTO> getIdealFoodItemsForBreakfast() {
		return getIdealFoodItemsByCourse(1);
	}

	/**
	 * Functions return list of ideal food items suggested for lunch.
	 * 
	 * @return {@link List<FoodItemDTO>} List of food items
	 */
	public List<FoodItemDTO> getIdealFoodItemsForLunch() {
		return getIdealFoodItemsByCourse(2);
	}

	/**
	 * Functions return list of ideal food items suggested for snacks.
	 * 
	 * @return {@link List<FoodItemDTO>} List of food items
	 */
	public List<FoodItemDTO> getIdealFoodItemsForSnacks() {
		return getIdealFoodItemsByCourse(3);
	}

	/**
	 * Functions return list of ideal food items suggested for dinner.
	 * 
	 * @return {@link List<FoodItemDTO>} List of food items
	 */
	public List<FoodItemDTO> getIdealFoodItemsForDinner() {
		return getIdealFoodItemsByCourse(4);
	}

	/**
	 * Gets list of suggested ideal food items for specific food course.
	 * 
	 * @param course
	 *            Food course
	 * @return {@link List<FoodItemDTO>} List of food items
	 */
	private List<FoodItemDTO> getIdealFoodItemsByCourse(int course) {
		List<IdealFoodItem> idealFoodItems = idealFoodItemRepository.findByCourse(course);
		List<FoodItemDTO> foodItemDTOs = new ArrayList<>();

		idealFoodItems.stream().forEach(idealFoodIteam -> {
			IdealFoodItemDTO idealFoodItemDTO = mapper.map(idealFoodIteam, IdealFoodItemDTO.class);
			foodItemDTOs.add(idealFoodItemDTO.getFoodItem());
		});

		return foodItemDTOs;
	}
}
