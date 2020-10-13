package com.alfonso.recipes.repository;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.alfonso.recipes.database.AppDataBase;
import com.alfonso.recipes.idling.RecipeIdling;
import com.alfonso.recipes.models.Ingredient;
import com.alfonso.recipes.models.Recipe;
import com.alfonso.recipes.models.RecipeResponse;
import com.alfonso.recipes.models.Step;
import com.alfonso.recipes.web.services.RecipesService;
import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Singleton
public class RecipeRepository implements IRecipeRepository {
    private final Context context;
    private final RecipesService recipesService;
    private final ConnectivityManager connectivityManager;
    private final String TAG = RecipeRepository.class.getSimpleName();
    private final AppDataBase dataBase;
    private MutableLiveData<List<Recipe>> recipes;

    @Inject
    public RecipeRepository(@ApplicationContext Context context, RecipesService service, AppDataBase dataBase) {
        recipesService = service;
        this.context = context;
        this.dataBase = dataBase;
        connectivityManager = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public LiveData<List<Recipe>> getRecipes() {
        if(recipes == null) {
            recipes = new MutableLiveData<>();
            loadData();
        }
        return recipes;
    }

    @Override
    public LiveData<List<Ingredient>> getIngredients(int id) {
        MutableLiveData<List<Ingredient>> ingredients = new MutableLiveData<>();
        Executor executorFind = new ThreadTaskExecutor();
        executorFind.execute(() -> {
            List<Ingredient> dataFromDataBase = dataBase.ingredientDao().getIngredientsFromRecipe(id);
            ingredients.postValue(dataFromDataBase);
        });
        return ingredients;
    }

    @Override
    public LiveData<List<Step>> getSteps(int id) {
        MutableLiveData<List<Step>> steps = new MutableLiveData<>();
        Executor executorFind = new ThreadTaskExecutor();
        executorFind.execute(() -> {
            List<Step> stepsFromDataBase = dataBase.stepDao().getStepsFromRecipe(id);
            steps.postValue(stepsFromDataBase);
        });
        return steps;
    }

    @Override
    public Cursor getIngredientsRaw(int id) {
        return dataBase.ingredientDao().rawGetIngredientsFromRecipe(id);
    }

    private boolean isConnected() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private void fetch() {
        recipesService.getRecipes().enqueue(new Callback<List<RecipeResponse>>() {
            @Override
            public void onResponse(Call<List<RecipeResponse>> call, Response<List<RecipeResponse>> response) {
                Log.d(TAG,"Loading from web" + response.toString());
                if(response.isSuccessful()) {
                    List<Recipe> aux = response.body().stream().map(r ->
                                new Recipe(r.getId(),r.getName(),r.getServings(),r.getImage(),r.getIngredients().size(),r.getSteps().size())).collect(Collectors.toList());
                    insertIntoDataBase(response.body(),aux);
                    recipes.setValue(aux);
                }
                RecipeIdling.getInstance().getIdling().decrement();
            }

            @Override
            public void onFailure(Call<List<RecipeResponse>> call, Throwable t) {
                Log.d(TAG,"onFailure " + call);
                Log.d(TAG,t.getMessage());
                RecipeIdling.getInstance().getIdling().decrement();
            }
        });
    }

    private void insertIntoDataBase(List<RecipeResponse> data,List<Recipe> recipeList) {
        Executor executorInsertDataBase = new ThreadTaskExecutor();
        executorInsertDataBase.execute(() -> {
            dataBase.recipeDao().insertRecipe(recipeList);
            for (RecipeResponse recipeResponse : data) {
                recipeResponse.getIngredients().forEach(ingredient -> ingredient.setRecipeId(recipeResponse.getId()));
                recipeResponse.getSteps().forEach(step -> step.setRecipeId(recipeResponse.getId()));
                dataBase.ingredientDao().insertIngredient(recipeResponse.getIngredients());
                dataBase.stepDao().insertRecipe(recipeResponse.getSteps());
            }
        });
    }

    public static class ThreadTaskExecutor implements Executor {
        @Override
        public void execute(Runnable runnable) {
            new Thread(runnable).start();
        }
    }

    private void loadData() {
        RecipeIdling.getInstance().getIdling().increment();
        Executor executorDataBaseRecipe = new ThreadTaskExecutor();
        executorDataBaseRecipe.execute(() -> {
            List<Recipe> data = dataBase.recipeDao().getAll();
            if(data.size() == 0) {
                fetch();
            } else  {
                Log.d(TAG,"Loading from database");
                recipes.postValue(data);
                RecipeIdling.getInstance().getIdling().decrement();
            }
        });
    }
}
