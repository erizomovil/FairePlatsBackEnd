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

import com.jorge.fairePlats.erros.ResourceNotFoundException;
import com.jorge.fairePlats.models.Ingredient;
import com.jorge.fairePlats.models.Recipe;
import com.jorge.fairePlats.repository.IngredientRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	IngredientRepository ingredientRepository;
	
    @GetMapping
    public List<Ingredient> obtenerTodosLosIngredientes() {
        return ingredientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Ingredient obtenerIngredientePorId(@PathVariable("id") Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrado"));
    }
    
}
