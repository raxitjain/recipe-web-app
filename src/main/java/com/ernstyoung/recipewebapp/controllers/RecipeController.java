package com.ernstyoung.recipewebapp.controllers;

import com.ernstyoung.recipewebapp.exceptions.NotFoundException;
import com.ernstyoung.recipewebapp.models.Recipe;
import com.ernstyoung.recipewebapp.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView pageNotFound(Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.setViewName("404error");
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView badRequestPage(Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.setViewName("400error");
        return modelAndView;
    }


}
