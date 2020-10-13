package com.alfonso.recipes.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.alfonso.recipes.models.Ingredient;
import com.alfonso.recipes.models.Step;
import com.alfonso.recipes.repository.IRecipeRepository;

import java.util.List;

public class RecipeDetailViewModel extends ViewModel {

    private final IRecipeRepository repository;
    private final MutableLiveData<Step> stepSelected;
    private int recipeId;

    public RecipeDetailViewModel(IRecipeRepository repository) {
        this.repository = repository;
        stepSelected = new MutableLiveData<>();
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public LiveData<List<Ingredient>> getIngredients() {
        Log.d(RecipeDetailViewModel.class.getName(),"RecipeId: " + recipeId);
        return repository.getIngredients(recipeId);
    }

    public LiveData<List<Step>> getSteps() {
        return repository.getSteps(recipeId);
    }

    public LiveData<Step> getSelected() {
        return stepSelected;
    }

    public void selectStep(Step step) {
        stepSelected.setValue(step);
    }
}

