package com.alfonso.recipes.viewModel;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.alfonso.recipes.models.Recipe;
import com.alfonso.recipes.repository.IRecipeRepository;

import java.util.List;

import dagger.hilt.android.scopes.ActivityRetainedScoped;


@ActivityRetainedScoped
public class ListRecipesViewModel extends ViewModel {
    private final String TAG = ListRecipesViewModel.class.getSimpleName();

    private final IRecipeRepository repository;
    private final SavedStateHandle savedStateHandle;
    private final LiveData<List<Recipe>> recipes;
    private final MutableLiveData<Recipe> recipeSelected;

    @ViewModelInject
    public ListRecipesViewModel(IRecipeRepository repository, @Assisted SavedStateHandle savedStateHandle) {
        this.repository = repository;
        this.savedStateHandle = savedStateHandle;
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
