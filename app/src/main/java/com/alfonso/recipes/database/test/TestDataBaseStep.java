package com.alfonso.recipes.database.test;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.alfonso.recipes.database.RecipeDao;
import com.alfonso.recipes.database.StepDao;
import com.alfonso.recipes.models.Recipe;
import com.alfonso.recipes.models.Step;

@Database(entities = {Step.class, Recipe.class}, version = 1, exportSchema = false)
public abstract class TestDataBaseStep extends RoomDatabase {
    public abstract StepDao stepDao();
    public abstract RecipeDao recipeDao();
}
