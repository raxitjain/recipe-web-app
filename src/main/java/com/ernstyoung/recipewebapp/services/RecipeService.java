package com.ernstyoung.recipewebapp.services;

import com.ernstyoung.recipewebapp.models.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

}
