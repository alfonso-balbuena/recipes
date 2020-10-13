package com.alfonso.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.alfonso.recipes.activity.RecipeDetailActivity;
import com.alfonso.recipes.viewModel.ListRecipesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private ListRecipesViewModel viewModel;
    public static final String RECIPE_ID = "recipeId";
    public static final String RECIPE_NAME = "nameRecipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(ListRecipesViewModel.class);

        viewModel.getSelected().observe(this, recipe -> {
            Intent detail = new Intent(this, RecipeDetailActivity.class);
            detail.putExtra(RECIPE_ID,recipe.getId());
            detail.putExtra(RECIPE_NAME,recipe.getName());
            startActivity(detail);
        });
    }
}