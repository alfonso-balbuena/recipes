package com.alfonso.recipes.di;

import com.alfonso.recipes.repository.IRecipeRepository;
import com.alfonso.recipes.repository.RecipeRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class RecipeRepositoryModule {
    @Singleton
    @Binds
    public abstract  IRecipeRepository provideRecipeRepository(RecipeRepository repository);
}
