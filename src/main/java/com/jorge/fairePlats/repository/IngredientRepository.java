package com.jorge.fairePlats.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorge.fairePlats.models.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

}
