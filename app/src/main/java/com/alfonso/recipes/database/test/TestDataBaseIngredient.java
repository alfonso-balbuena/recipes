package com.alfonso.recipes.database.test;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.alfonso.recipes.database.IngredientDao;
import com.alfonso.recipes.database.RecipeDao;
import com.alfonso.recipes.models.Ingredient;
import com.alfonso.recipes.models.Recipe;


@Database(entities = {Ingredient.class, Recipe.class}, version = 1, exportSchema = false)
public abstract class TestDataBaseIngredient extends RoomDatabase {
    public abstract IngredientDao ingredientDao();
    public abstract RecipeDao recipeDao();
}
