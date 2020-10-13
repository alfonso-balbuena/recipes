package com.alfonso.recipes.repository;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import com.alfonso.recipes.models.Ingredient;
import com.alfonso.recipes.models.Recipe;
import com.alfonso.recipes.models.Step;


import java.util.List;

public interface IRecipeRepository {
    LiveData<List<Recipe>> getRecipes();
    LiveData<List<Ingredient>> getIngredients(int id);
    LiveData<List<Step>> getSteps(int id);
    Cursor getIngredientsRaw(int id);
}
