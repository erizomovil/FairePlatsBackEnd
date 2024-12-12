package com.jorge.fairePlats.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredientsrecipes")
public class IngredientRecipeRelation {
	 @EmbeddedId
	    private IngredientsRecipeId id;

	    @ManyToOne
	    @MapsId("idRecipe")
	    @JoinColumn(name = "id_recipe", referencedColumnName = "id", nullable = false)
	    private Recipe recipe;

	    @ManyToOne
	    @MapsId("idIngredient")
	    @JoinColumn(name = "id_ingredient", referencedColumnName = "id", nullable = false)
	    private Ingredient ingredient;

	    @Column(nullable = false)
	    private String quantity;

	    // Getters and Setters

	    public IngredientsRecipeId getId() {
	        return id;
	    }

	    public void setId(IngredientsRecipeId id) {
	        this.id = id;
	    }

	    public Recipe getRecipe() {
	        return recipe;
	    }

	    public void setRecipe(Recipe recipe) {
	        this.recipe = recipe;
	    }

	    public Ingredient getIngredient() {
	        return ingredient;
	    }

	    public void setIngredient(Ingredient ingredient) {
	        this.ingredient = ingredient;
	    }

	    public String getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(String quantity) {
	        this.quantity = quantity;
	    }
	}