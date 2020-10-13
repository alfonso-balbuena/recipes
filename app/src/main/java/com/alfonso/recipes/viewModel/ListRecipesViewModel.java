package com.alfonso.recipes.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.alfonso.recipes.models.Recipe;
import com.alfonso.recipes.repository.IRecipeRepository;

import java.util.List;

public class ListRecipesViewModel extends ViewModel {
    private final String TAG = ListRecipesViewModel.class.getSimpleName();

    private final IRecipeRepository repository;
    private final LiveData<List<Recipe>> recipes;
    private final MutableLiveData<Recipe> recipeSelected;

    public ListRecipesViewModel(IRecipeRepository repository) {
        this.repository = repository;
        recipeSelected = new MutableLiveData<>();
        recipes = this.repository.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }

    public LiveData<Recipe> getSelected() {
        return recipeSelected;
    }

    public void select(Recipe recipeS) {
        recipeSelected.setValue(recipeS);
    }
}
