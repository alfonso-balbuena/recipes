package com.alfonso.recipes.di;

import com.alfonso.recipes.web.services.RecipesService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceModule {

    public static RecipesService provideRecipeService() {
        return new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RecipesService.class);

    }
}
