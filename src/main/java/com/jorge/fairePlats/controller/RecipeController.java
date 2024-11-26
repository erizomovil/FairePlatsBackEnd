package com.jorge.fairePlats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.fairePlats.models.Recipe;
import com.jorge.fairePlats.erros.ResourceNotFoundException;
import com.jorge.fairePlats.repository.RecipeRepository;

@CrossOrigin(origins="")
@RestController
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	RecipeRepository recipeRepository;
	
    @GetMapping
    public List<Recipe> obtenerTodosLosPlatos() {
        return recipeRepository.findAll();
    }

    @PostMapping
    public Recipe crearPlato(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @GetMapping("/{id}")
    public Recipe obtenerPlatoPorId(@PathVariable("id") Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado"));
    }

    @PutMapping("/{id}")
    public Recipe actualizarPlato(@PathVariable("id") Long id, @RequestBody Recipe detallesRecipe) {
    	Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado"));

    	recipe.setTitle(detallesRecipe.getTitle());
    	recipe.setDifficulty(detallesRecipe.getDifficulty());
    	recipe.setTime(detallesRecipe.getTime());

        return recipeRepository.save(recipe);
    }

}
