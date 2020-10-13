package com.alfonso.recipes.repository;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alfonso.recipes.idling.RecipeIdling;
import com.alfonso.recipes.models.Ingredient;
import com.alfonso.recipes.models.Recipe;
import com.alfonso.recipes.models.Step;

import java.util.ArrayList;
import java.util.List;

public class MockIngredientsRepository implements IRecipeRepository {

    @Override
    public LiveData<List<Recipe>> getRecipes() {
        MutableLiveData<List<Recipe>> recipes = new MutableLiveData<>();
        List<Recipe> data = new ArrayList<Recipe>() {{
            add(new Recipe(1,"Nutella Pie",8,"",1,1));
            add(new Recipe(2,"Brownies",8,"",1,1));
            add(new Recipe(3,"Yellow Cake",8,"",1,1));
            add(new Recipe(4,"Cheesecake",8,"",1,1));
        }};
        recipes.setValue(data);
        return recipes;
    }

    @Override
    public LiveData<List<Ingredient>> getIngredients(int id) {
        MutableLiveData<List<Ingredient>> ingredients = new MutableLiveData<>();
        RecipeRepository.ThreadTaskExecutor executor = new RecipeRepository.ThreadTaskExecutor();
        RecipeIdling.getInstance().getIdling().increment();
        executor.execute(() -> {
            try {
                Thread.sleep(300);
                ingredients.postValue(MockUtils.getMockIngredients());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RecipeIdling.getInstance().getIdling().decrement();
        });

        return ingredients;
    }

    @Override
    public LiveData<List<Step>> getSteps(int id) {
        MutableLiveData<List<Step>> steps = new MutableLiveData<>();
        return steps;
    }

    @Override
    public Cursor getIngredientsRaw(int id) {
        return null;
    }
}
