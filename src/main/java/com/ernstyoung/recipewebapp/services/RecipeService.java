package com.ernstyoung.recipewebapp.services;

import com.ernstyoung.recipewebapp.models.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe getRecipeById(Long id);
    Recipe saveRecipe(Recipe command);
    void deleteRecipeById(Long id);

}
