package com.mindfire.dietplanner.core.component;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindfire.dietplanner.core.dto.NutrientDTO;
import com.mindfire.dietplanner.core.entity.Nutrient;
import com.mindfire.dietplanner.core.repository.NutrientRepository;

/**
 * NutrientComponent class implements get and set methods for nutrition values
 * associated with each food item.
 */
@Component
public class NutrientComponent {

	@Autowired
	NutrientRepository nutrientRepository;

	ModelMapper mapper;
	NutrientDTO nutrientDTO;

	public NutrientComponent() {
		// Default constructor
		mapper = new ModelMapper();
	}

	/**
	 * Gets nutrients data from database.
	 * 
	 * @param id
	 *            Nutrients ID
	 * @return {@link NutrientDTO} Nutrient data
	 */
	public NutrientDTO getNutrient(int id) {
		Nutrient nutrient = nutrientRepository.findOne(id); // Get entity
		nutrientDTO = mapper.map(nutrient, NutrientDTO.class); // Map to DTO

		return nutrientDTO;
	}

	/**
	 * Saves nutrients data to database.
	 * 
	 * @param nutrientDTO
	 *            Nutrient data
	 * @return {@link NutrientDTO} Saved nutrient data
	 */
	public NutrientDTO setNutrient(NutrientDTO nutrientDTO) {
		Nutrient nutrient = mapper.map(nutrientDTO, Nutrient.class); // Map DTO to entity
		nutrient = nutrientRepository.save(nutrient); // Save entity

		nutrientDTO = mapper.map(nutrient, NutrientDTO.class); // Map back to DTO
		return nutrientDTO;
	}

}
