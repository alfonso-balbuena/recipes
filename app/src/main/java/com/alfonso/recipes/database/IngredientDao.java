package com.alfonso.recipes.database;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.alfonso.recipes.models.Ingredient;


import java.util.List;

@Dao
public interface IngredientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertIngredient(List<Ingredient> ingredients);

    @Query("SELECT * FROM ingredients WHERE recipeId =:recipeId")
    List<Ingredient> getIngredientsFromRecipe(final int recipeId);

    @Query("SELECT * FROM ingredients WHERE recipeId =:recipeId")
    Cursor rawGetIngredientsFromRecipe(final int recipeId);
}
