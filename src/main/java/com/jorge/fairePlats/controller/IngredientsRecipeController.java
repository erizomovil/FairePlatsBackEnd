package com.jorge.fairePlats.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jorge.fairePlats.models.IngredientRecipeRelation;
import com.jorge.fairePlats.models.IngredientsRecipeId;
import com.jorge.fairePlats.repository.IngredientRepository;
import com.jorge.fairePlats.repository.IngredientsRecipeRepository;
import com.jorge.fairePlats.repository.RecipeRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/ingredientsRecipes")
public class IngredientsRecipeController {

    @Autowired
    private IngredientsRecipeRepository ingredientsRecipeRepository;
    
    @Autowired
    private RecipeRepository recipeRepository;
    
    @Autowired
    private IngredientRepository ingredientRepository; 
    
    @PostMapping
    public ResponseEntity<IngredientRecipeRelation> createIngredientsRecipe(@RequestBody IngredientRecipeRelation ingredientsRecipe) {
        if (ingredientsRecipe.getId() == null) {
            IngredientsRecipeId id = new IngredientsRecipeId(
                ingredientsRecipe.getRecipe().getId(),
                ingredientsRecipe.getIngredient().getId()
            );
            ingredientsRecipe.setId(id);
        }

        IngredientRecipeRelation savedIngredientsRecipe = ingredientsRecipeRepository.save(ingredientsRecipe);
        return new ResponseEntity<>(savedIngredientsRecipe, HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<IngredientRecipeRelation> getAllIngredientsRecipes() {
        return ingredientsRecipeRepository.findAll();
    }
    
    @GetMapping("/{idRecipe}/{idIngredient}")
    public ResponseEntity<IngredientRecipeRelation> getIngredientsRecipe(@PathVariable Long idRecipe, @PathVariable Long idIngredient) {
        IngredientsRecipeId id = new IngredientsRecipeId(idRecipe, idIngredient);
        return ingredientsRecipeRepository.findById(id)
                .map(ingredientsRecipe -> new ResponseEntity<>(ingredientsRecipe, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/bulk")
    public ResponseEntity<List<IngredientRecipeRelation>> createIngredientsRecipesBulk(
            @RequestBody List<IngredientRecipeRelation> ingredientsRecipes) {
        
        List<IngredientRecipeRelation> savedRelations = new ArrayList<>();
        
        for (IngredientRecipeRelation relation : ingredientsRecipes) {
            if (relation.getRecipe() == null || relation.getIngredient() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
            // Verificar si los IDs son válidos
            if (!recipeRepository.existsById(relation.getRecipe().getId()) || 
                !ingredientRepository.existsById(relation.getIngredient().getId())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // Crear el ID compuesto manualmente si es necesario
            IngredientsRecipeId id = new IngredientsRecipeId(
                relation.getRecipe().getId(),
                relation.getIngredient().getId()
            );
            
            relation.setId(id); // Asignar el ID compuesto

            // Guardar la relación
            savedRelations.add(ingredientsRecipeRepository.save(relation));
        }
        
        return new ResponseEntity<>(savedRelations, HttpStatus.CREATED);
    }


    @DeleteMapping("/{idRecipe}/{idIngredient}")
    public ResponseEntity<Void> deleteIngredientsRecipe(@PathVariable Long idRecipe, @PathVariable Long idIngredient) {
        IngredientsRecipeId id = new IngredientsRecipeId(idRecipe, idIngredient);
        return ingredientsRecipeRepository.findById(id)
                .map(existingIngredientsRecipe -> {
                    ingredientsRecipeRepository.delete(existingIngredientsRecipe);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
