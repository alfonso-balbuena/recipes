package com.alfonso.recipes.database.test;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.alfonso.recipes.database.RecipeDao;
import com.alfonso.recipes.models.Recipe;


@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class TestDataBaseRecipe extends RoomDatabase {
    public abstract RecipeDao recipeDao();
}
