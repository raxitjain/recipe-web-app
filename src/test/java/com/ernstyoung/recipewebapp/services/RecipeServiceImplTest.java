package com.ernstyoung.recipewebapp.services;

import com.ernstyoung.recipewebapp.exceptions.NotFoundException;
import com.ernstyoung.recipewebapp.models.Recipe;
import com.ernstyoung.recipewebapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() {
            MockitoAnnotations.openMocks(this);
            recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdNotFound() {
        Optional<Recipe> recipe = Optional.empty();
        when(recipeRepository.findById(anyLong())).thenReturn(recipe);
        Recipe recipeReturned = recipeService.getRecipeById(1L);

    }

    @Test
    public void getRecipes() {
        Recipe recipe1 = new Recipe();
        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe1);

        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        Recipe recipeReturned = recipeService.getRecipeById(1L);
        assertNotNull(recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void deleteRecipeById() {
        recipeService.deleteRecipeById(1L);
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}