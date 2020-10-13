package com.alfonso.recipes.widget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.alfonso.recipes.R;
import com.alfonso.recipes.models.Recipe;
import com.alfonso.recipes.viewModel.ListRecipesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ConfigureWidget extends AppCompatActivity {

    private ListRecipesViewModel viewModel;
    public static final String RECIPE_ID_WIDGET = "recipeId";
    public static final String RECIPE_NAME_WIDGET = "nameRecipe";
    public static final String WIDGET_PREFERENCE = "widgetPreference";
    int mWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(RESULT_CANCELED);
        setContentView(R.layout.activity_configure_widget);
        setTitle(getString(R.string.configure_widget));
        viewModel = new ViewModelProvider(this).get(ListRecipesViewModel.class);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null) {
            mWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if(mWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }


        viewModel.getSelected().observe(this, recipe -> {
            saveData(recipe);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            RecipesWidget.updateAppWidget(this,appWidgetManager,mWidgetId);
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,mWidgetId);
            setResult(RESULT_OK,resultValue);
            finish();
        });
    }

    private void saveData(Recipe recipe) {
        SharedPreferences.Editor editorPreference = ConfigureWidget.this.getSharedPreferences(WIDGET_PREFERENCE,0).edit();
        editorPreference.putInt(RECIPE_ID_WIDGET,recipe.getId());
        editorPreference.putString(RECIPE_NAME_WIDGET,recipe.getName());
        editorPreference.commit();
    }
}