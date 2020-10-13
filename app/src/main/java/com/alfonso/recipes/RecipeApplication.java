package com.alfonso.recipes;

import android.app.Application;

import com.alfonso.recipes.di.DataBaseModule;
import com.alfonso.recipes.di.WebServiceModule;
import com.alfonso.recipes.repository.IRecipeRepository;
import com.alfonso.recipes.repository.RecipeRepository;

public class RecipeApplication extends Application {
    public IRecipeRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        repository = new RecipeRepository(this, WebServiceModule.provideRecipeService(), DataBaseModule.provideAppDataBase(this));
    }
}
