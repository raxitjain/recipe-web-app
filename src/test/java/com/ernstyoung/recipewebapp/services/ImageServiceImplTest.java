package com.ernstyoung.recipewebapp.services;

import com.ernstyoung.recipewebapp.models.Recipe;
import com.ernstyoung.recipewebapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {

    ImageServiceImpl imageService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void saveImageFile() {
        try {
            MockMultipartFile mockMultipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                    "Recipe Web App".getBytes());
            Recipe recipe = new Recipe();
            recipe.setId(1L);
            Optional<Recipe> recipeOptional = Optional.of(recipe);
            when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
            ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);
            imageService.saveImageFile(1L, mockMultipartFile);
            verify(recipeRepository, times(1)).save(argumentCaptor.capture());
            Recipe savedRecipe = argumentCaptor.getValue();
            assertEquals(mockMultipartFile.getBytes().length, savedRecipe.getImage().length);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}