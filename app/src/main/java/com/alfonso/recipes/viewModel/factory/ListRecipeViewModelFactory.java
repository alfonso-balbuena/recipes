package com.alfonso.recipes.viewModel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.alfonso.recipes.repository.IRecipeRepository;
import com.alfonso.recipes.viewModel.ListRecipesViewModel;

public class ListRecipeViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final IRecipeRepository repository;

    public  ListRecipeViewModelFactory(IRecipeRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListRecipesViewModel(repository);
    }
}
