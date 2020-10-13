package com.alfonso.recipes.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.alfonso.recipes.models.Step;
import java.util.List;
@Dao
public interface StepDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRecipe(List<Step> steps);

    @Query("SELECT * FROM steps WHERE recipeId =:recipeId ORDER BY numberStep")
    List<Step> getStepsFromRecipe(final int recipeId);
}
