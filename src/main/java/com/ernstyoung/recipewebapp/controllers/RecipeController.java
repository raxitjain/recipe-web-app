package com.ernstyoung.recipewebapp.controllers;

import com.ernstyoung.recipewebapp.models.Recipe;
import com.ernstyoung.recipewebapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/recipe")
@Controller
public class RecipeController {

    private final RecipeService recipeService;

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
        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new Recipe());
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("/add")
    public String saveOrUpdate(@ModelAttribute Recipe command) {
        Recipe savedRecipe = recipeService.saveRecipe(command);
        return "redirect:/recipe/show/" + savedRecipe.getId();
    }

    @GetMapping
    @RequestMapping("/{id}/delete")
    public String deleteRecipeById(@PathVariable String id, Model model) {
        recipeService.deleteRecipeById(new Long(id));
        return "redirect:/";
    }

}
