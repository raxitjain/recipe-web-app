package com.ernstyoung.recipewebapp.controllers;

import com.ernstyoung.recipewebapp.models.Ingredient;
import com.ernstyoung.recipewebapp.services.IngredientService;
import com.ernstyoung.recipewebapp.services.RecipeService;
import com.ernstyoung.recipewebapp.services.UomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IngredientController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UomService uomService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService, UomService uomService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.uomService = uomService;
    }

    @GetMapping("/recipe/{id}/ingredient")
    public String listIngredients(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(new Long(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findIngredient(recipeId, id));
        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateIngredientForm(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("uomList", uomService.listAllUoms());
        model.addAttribute("ingredient", ingredientService.findIngredient(recipeId, id));
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));
        return "redirect:/recipe/" + recipeId + "/ingredient";
    }

    @PostMapping("/recipe/{recipeId}/update-ingredient")
    public String updateIngredient(@PathVariable String recipeId, @ModelAttribute Ingredient ingredient) {
        Ingredient savedIgredient = ingredientService.saveIngredient(new Long(recipeId), ingredient);
        return "redirect:/recipe/" + recipeId + "/ingredient/" + savedIgredient.getId() + "/show";
    }

}
