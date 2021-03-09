package com.ernstyoung.recipewebapp.services;

import com.ernstyoung.recipewebapp.models.Ingredient;
import com.ernstyoung.recipewebapp.models.Recipe;
import com.ernstyoung.recipewebapp.repositories.RecipeRepository;
import com.ernstyoung.recipewebapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Ingredient findIngredient(String recipeId, String ingredientId) {
        Recipe recipe = recipeRepository.findById(new Long(recipeId)).orElse(null);
        for (Ingredient ingredient : recipe.getIngredients()) {
            if (ingredient.getId().equals(new Long(ingredientId))) {
                return ingredient;
            }
        }
        return null;
    }

    @Override
    public Ingredient saveIngredient(Long recipeId, Ingredient ingredient) {
        String uomDescription = unitOfMeasureRepository.findById(ingredient.getUom().getId()).orElse(null).getUom();
        ingredient.getUom().setUom(uomDescription);
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                .filter(ingredient1 -> ingredient1.getId().equals(ingredient.getId())).findFirst();
        if (ingredientOptional.isPresent()) {
            ingredientOptional.get().setAmount(ingredient.getAmount());
            ingredientOptional.get().setDescription(ingredient.getDescription());
            ingredientOptional.get().setUom(ingredient.getUom());
        } else {
            recipe.addIngredient(ingredient);
        }
        Recipe savedRecipe = recipeRepository.save(recipe);
        for (Ingredient ingredient1 : savedRecipe.getIngredients()) {
            if (ingredient1.getId().equals(ingredient.getId())) {
                return Optional.of(ingredient1).get();
            }
        }
        return Optional.<Ingredient>empty().get();
    }

    @Override
    public void deleteById(Long recipeId, Long id) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        Optional<Ingredient> ingredientOptional = Optional.empty();
        for (Ingredient ingredient1 : recipe.getIngredients()) {
            if (ingredient1.getId().equals(id)) {
                ingredientOptional = Optional.of(ingredient1);
                break;
            }
        }
        if (ingredientOptional.isPresent()) {
            ingredientOptional.get().setRecipe(null);
            recipe.getIngredients().remove(ingredientOptional.get());
        }
        recipeRepository.save(recipe);
    }
}
