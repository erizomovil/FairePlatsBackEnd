package com.jorge.fairePlats.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorge.fairePlats.models.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

}
