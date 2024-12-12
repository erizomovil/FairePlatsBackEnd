package com.jorge.fairePlats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jorge.fairePlats.models.IngredientRecipeRelation;
import com.jorge.fairePlats.models.IngredientsRecipeId;

public interface IngredientsRecipeRepository extends JpaRepository<IngredientRecipeRelation, IngredientsRecipeId> {

}
