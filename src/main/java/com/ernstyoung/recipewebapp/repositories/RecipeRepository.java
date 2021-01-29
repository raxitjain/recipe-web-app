package com.ernstyoung.recipewebapp.repositories;

import com.ernstyoung.recipewebapp.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
