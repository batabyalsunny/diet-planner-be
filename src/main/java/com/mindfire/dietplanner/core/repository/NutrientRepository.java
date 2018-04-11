package com.mindfire.dietplanner.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.dietplanner.core.entity.Nutrient;

/**
 * NutrientRepository acts as a CRUD interface.
 */
@Repository
public interface NutrientRepository extends CrudRepository<Nutrient, Integer> {
	
}
