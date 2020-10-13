package com.alfonso.recipes.repository;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alfonso.recipes.idling.RecipeIdling;
import com.alfonso.recipes.models.Ingredient;
import com.alfonso.recipes.models.Recipe;
import com.alfonso.recipes.models.Step;

import java.util.List;

public class MockStepsRepository implements IRecipeRepository {
    @Override
    public LiveData<List<Recipe>> getRecipes() {
        return null;
    }

    @Override
    public LiveData<List<Ingredient>> getIngredients(int id) {
        MutableLiveData<List<Ingredient>> ingredientMutableLiveData = new MutableLiveData<>();
        return ingredientMutableLiveData;
    }

    @Override
    public LiveData<List<Step>> getSteps(int id) {
        MutableLiveData<List<Step>> steps = new MutableLiveData<>();
        RecipeRepository.ThreadTaskExecutor executor = new RecipeRepository.ThreadTaskExecutor();
        RecipeIdling.getInstance().getIdling().increment();
        executor.execute(() -> {
            try {
                Thread.sleep(300);
                steps.postValue(MockUtils.getMockSteps());
                RecipeIdling.getInstance().getIdling().decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return steps;
    }

    @Override
    public Cursor getIngredientsRaw(int id) {
        return null;
    }
}
