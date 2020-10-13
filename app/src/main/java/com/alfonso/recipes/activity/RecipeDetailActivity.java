package com.alfonso.recipes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;

import com.alfonso.recipes.MainActivity;
import com.alfonso.recipes.R;
import com.alfonso.recipes.models.Step;
import com.alfonso.recipes.viewModel.RecipeDetailViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecipeDetailActivity extends AppCompatActivity {
    public final static String STEP_SELECTED = "step_selected";
    public final static String LIST_STEP = "list_step";
    public final static String RECIPE_NAME = "recipe_name";
    private RecipeDetailViewModel viewModel;
    private int id;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        AtomicReference<List<Step>> steps = new AtomicReference<>();
        Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.RECIPE_ID)) {
            name = intent.getStringExtra(MainActivity.RECIPE_NAME);
            id = intent.getIntExtra(MainActivity.RECIPE_ID,-1);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(name);
            viewModel = new ViewModelProvider(this).get(RecipeDetailViewModel.class);
            viewModel.setRecipeId(id);
            viewModel.getSteps().observe(this, stepsList -> {
                steps.set(stepsList);
            });
            viewModel.getSelected().observe(this, step -> {
                FragmentContainerView fragmentContainerView = findViewById(R.id.fragment_step_detail);
                if(fragmentContainerView == null) {
                    Intent stepDetail = new Intent(this,StepDetailActivity.class);
                    stepDetail.putExtra(STEP_SELECTED,step);
                    List<Step> aux = steps.get();
                    stepDetail.putParcelableArrayListExtra(LIST_STEP, (ArrayList<? extends Parcelable>) aux);
                    stepDetail.putExtra(RECIPE_NAME,name);
                    startActivity(stepDetail);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}