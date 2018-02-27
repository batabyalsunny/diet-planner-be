package com.mindfire.dietplanner.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.dietplanner.core.component.NutrientComponent;
import com.mindfire.dietplanner.core.dto.NutrientDTO;

/**
 * NutrientService implements the business logic for Nutrient. It also
 * implements basic CRUD implementations.
 */
@Service
public class NutrientService {

	@Autowired
	NutrientComponent nutrientComponent;

	public NutrientService() {
		// Default constructor
	}

	/**
	 * Gets nutrients data by ID.
	 * 
	 * @param id
	 *            Nutrient ID
	 * @return Nutrient data
	 */
	public NutrientDTO getNutrient(int id) {
		return nutrientComponent.getNutrient(id);
	}

	/**
	 * Saves a new nutrients data.
	 * 
	 * @param newNutrientDTO
	 *            Nutrients data
	 * @return Saved nutrients data
	 */
	public NutrientDTO setNutrient(NutrientDTO newNutrientDTO) {
		return nutrientComponent.setNutrient(newNutrientDTO);
	}
}
