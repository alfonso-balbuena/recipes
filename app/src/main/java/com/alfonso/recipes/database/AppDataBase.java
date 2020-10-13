package com.alfonso.recipes.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.alfonso.recipes.models.Ingredient;
import com.alfonso.recipes.models.Recipe;
import com.alfonso.recipes.models.Step;

@Database(entities = {Recipe.class, Ingredient.class, Step.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract IngredientDao ingredientDao();
    public abstract RecipeDao recipeDao();
    public abstract StepDao stepDao();

}
