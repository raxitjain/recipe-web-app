package com.ernstyoung.recipewebapp.controllers;

import com.ernstyoung.recipewebapp.models.Recipe;
import com.ernstyoung.recipewebapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/recipe")
@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;
    private final static String RECIPE_FORM_URL = "recipe/recipeform";

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/show/{id}")
    public String getRecipeById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(new Long(id)));
        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(new Long(id)));
        return RECIPE_FORM_URL;
    }

    @GetMapping
    @RequestMapping("/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new Recipe());
        return RECIPE_FORM_URL;
    }

    @PostMapping
    @RequestMapping("/add")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.error(objectError.toString());
            });
            return RECIPE_FORM_URL;
        }
        Recipe savedRecipe = recipeService.saveRecipe(recipe);
        return "redirect:/recipe/show/" + savedRecipe.getId();
    }

    @GetMapping
    @RequestMapping("/{id}/delete")
    public String deleteRecipeById(@PathVariable String id, Model model) {
        recipeService.deleteRecipeById(new Long(id));
        return "redirect:/";
    }

}
