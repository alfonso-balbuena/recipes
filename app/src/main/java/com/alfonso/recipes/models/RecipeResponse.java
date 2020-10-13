package com.alfonso.recipes.models;

import java.util.List;

public class RecipeResponse extends Recipe {
    private List<Step> steps;
    private List<Ingredient> ingredients;

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
