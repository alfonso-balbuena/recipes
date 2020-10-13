package com.alfonso.recipes.web.services;

import com.alfonso.recipes.models.RecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesService {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<RecipeResponse>> getRecipes();
}
