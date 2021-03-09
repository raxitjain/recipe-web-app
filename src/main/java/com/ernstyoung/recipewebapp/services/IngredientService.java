package com.ernstyoung.recipewebapp.services;

import com.ernstyoung.recipewebapp.models.Ingredient;

public interface IngredientService {

    Ingredient findIngredient(String recipeId, String ingredientId);
    Ingredient saveIngredient(Long recipeId, Ingredient ingredient);
    void deleteById(Long recipeId, Long id);

}
