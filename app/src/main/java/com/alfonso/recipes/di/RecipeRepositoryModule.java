package com.alfonso.recipes.di;

import com.alfonso.recipes.repository.IRecipeRepository;
import com.alfonso.recipes.repository.RecipeRepository;

public abstract class RecipeRepositoryModule {
    public abstract  IRecipeRepository provideRecipeRepository(RecipeRepository repository);
}
