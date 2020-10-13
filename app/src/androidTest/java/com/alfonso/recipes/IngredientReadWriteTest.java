package com.alfonso.recipes;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.alfonso.recipes.database.IngredientDao;
import com.alfonso.recipes.database.RecipeDao;
import com.alfonso.recipes.database.test.TestDataBaseIngredient;
import com.alfonso.recipes.models.Ingredient;
import com.alfonso.recipes.models.Recipe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class IngredientReadWriteTest {

    private IngredientDao ingredientDao;
    private RecipeDao recipeDao;
    private TestDataBaseIngredient dataBase;

    @Before
    public void initDB() {
        Context context = ApplicationProvider.getApplicationContext();
        dataBase = Room.inMemoryDatabaseBuilder(context, TestDataBaseIngredient.class).allowMainThreadQueries().build();
        ingredientDao = dataBase.ingredientDao();
        recipeDao = dataBase.recipeDao();
    }

    @Test
    public void writeRecipesAndRead() {
        List<Recipe> recipes = new ArrayList<Recipe>() {{
            add(new Recipe(1,"Nutella Pie",8,"",2,1));
            add(new Recipe(2,"Brownies",8,"",2,1));
        }};
        recipeDao.insertRecipe(recipes);
        List<Ingredient> data = new ArrayList<Ingredient>() {{
                    add(new Ingredient(2,"CUP","Graham Cracker crumbs",1));
                    add(new Ingredient(6,"TBLSP","unsalted butter, melted",1));
                    add(new Ingredient(350,"G","Bittersweet chocolate (60-70% cacao)",2));
        }};

        ingredientDao.insertIngredient(data);
        List<Ingredient> aux = ingredientDao.getIngredientsFromRecipe(1);
        Assert.assertEquals((long) aux.size(),2);
        aux = ingredientDao.getIngredientsFromRecipe(2);
        Assert.assertEquals((long) aux.size(),1);
    }

}
