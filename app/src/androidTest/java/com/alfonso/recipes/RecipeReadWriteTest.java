package com.alfonso.recipes;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.alfonso.recipes.database.RecipeDao;
import com.alfonso.recipes.database.test.TestDataBaseRecipe;
import com.alfonso.recipes.models.Recipe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(AndroidJUnit4.class)
public class RecipeReadWriteTest {
    private RecipeDao recipeDao;
    private TestDataBaseRecipe dataBase;

    @Before
    public void initDB() {
        Context context = ApplicationProvider.getApplicationContext();
        dataBase = Room.inMemoryDatabaseBuilder(context, TestDataBaseRecipe.class).allowMainThreadQueries().build();
        recipeDao = dataBase.recipeDao();
    }

    @Test
    public void writeRecipesAndRead() {
        List<Recipe> data = new ArrayList<Recipe>() {{
            add(new Recipe(1,"Nutella Pie",8,"",2,1));
            add(new Recipe(2,"Brownies",8,"",2,1));
            add(new Recipe(3,"Yellow Cake",8,"",2,1));
            add(new Recipe(4,"Cheesecake",8,"",2,1));
        }};
        recipeDao.insertRecipe(data);
        List<Recipe> aux = recipeDao.getAll();
        Assert.assertEquals((long) aux.size(),(long)data.size());
        Optional<Recipe> find = aux.stream().filter(recipe -> recipe.getId() == 1).findFirst();
        Assert.assertFalse(!find.isPresent());
        Assert.assertEquals(find.get().getName(),"Nutella Pie");
    }

}
