package com.alfonso.recipes;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.alfonso.recipes.database.RecipeDao;
import com.alfonso.recipes.database.StepDao;
import com.alfonso.recipes.database.test.TestDataBaseStep;
import com.alfonso.recipes.models.Recipe;
import com.alfonso.recipes.models.Step;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class StepReadWriteTest {
    private StepDao stepDao;
    private TestDataBaseStep dataBase;
    private RecipeDao recipeDao;

    @Before
    public void initDB() {
        Context context = ApplicationProvider.getApplicationContext();
        dataBase = Room.inMemoryDatabaseBuilder(context, TestDataBaseStep.class).allowMainThreadQueries().build();
        stepDao = dataBase.stepDao();
        recipeDao = dataBase.recipeDao();
    }

    @Test
    public void writeRecipesAndRead() {
        List<Recipe> recipes = new ArrayList<Recipe>() {{
            add(new Recipe(1,"Nutella Pie",8,"",2,1));
            add(new Recipe(2,"Brownies",8,"",2,1));
        }};
        recipeDao.insertRecipe(recipes);

        List<Step> data = new ArrayList<Step>() {{
            add(new Step(1,"Starting prep","1. Preheat the oven to 350\u00b0F. Butter a 9\" deep dish pie pan.","","",1));
            add(new Step(0,"Recipe Introduction","Recipe Introduction","https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4","",1));
            add(new Step(0,"Recipe Introduction","Recipe Introduction.","https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdc33_-intro-brownies/-intro-brownies.mp4","",2));
        }};

        stepDao.insertRecipe(data);

        List<Step> aux = stepDao.getStepsFromRecipe(1);
        Assert.assertEquals((long) aux.size(),2);
        Assert.assertEquals(aux.get(0).getNumberStep(),0);
        aux = stepDao.getStepsFromRecipe(2);
        Assert.assertEquals((long) aux.size(),1);
    }

}
