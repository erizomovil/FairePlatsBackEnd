package com.jorge.fairePlats.models;

import java.io.Serializable;
import java.util.Objects;

public class IngredientsRecipeId implements Serializable {

    private Long idRecipe;
    private Long idIngredient;

    public IngredientsRecipeId() {
    }

    public IngredientsRecipeId(Long idRecipe, Long idIngredient) {
        if (idRecipe == null || idIngredient == null) {
            throw new IllegalArgumentException("idRecipe and idIngredient cannot be null");
        }
        this.idRecipe = idRecipe;
        this.idIngredient = idIngredient;
    }

    // Getters and Setters

    public Long getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Long idRecipe) {
        this.idRecipe = idRecipe;
    }

    public Long getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Long idIngredient) {
        this.idIngredient = idIngredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientsRecipeId that = (IngredientsRecipeId) o;
        return Objects.equals(idRecipe, that.idRecipe) && Objects.equals(idIngredient, that.idIngredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRecipe, idIngredient);
    }
}
