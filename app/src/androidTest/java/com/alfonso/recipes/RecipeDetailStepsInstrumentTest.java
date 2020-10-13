package com.alfonso.recipes;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.alfonso.recipes.activity.RecipeDetailActivity;
import com.alfonso.recipes.di.RecipeRepositoryModule;
import com.alfonso.recipes.idling.RecipeIdling;
import com.alfonso.recipes.repository.IRecipeRepository;
import com.alfonso.recipes.repository.MockStepsRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dagger.hilt.android.testing.BindValue;
import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.UninstallModules;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@UninstallModules(RecipeRepositoryModule.class)
@HiltAndroidTest
@RunWith(AndroidJUnit4.class)
public class RecipeDetailStepsInstrumentTest {

    @BindValue
    IRecipeRepository repository = new MockStepsRepository();
    @Rule(order = 0)
    public HiltAndroidRule rule = new HiltAndroidRule(this);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(RecipeIdling.getInstance().getIdling());
    }

    @Test
    public void testLaunchDetailActivitySteps() {
        ActivityScenario<RecipeDetailActivity> activityScenario = ActivityScenario.launch(initIntent());
        onView(withText("Recipe Introduction")).check(matches(isDisplayed()));
        onView(withText("Starting prep")).check(matches(isDisplayed()));
        onView(withText("Prep the cookie crust.")).check(matches(isDisplayed()));
    }

    private Intent initIntent() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), RecipeDetailActivity.class);
        intent.putExtra(MainActivity.RECIPE_NAME,"Nutella Pie");
        intent.putExtra(MainActivity.RECIPE_ID,0);
        return intent;
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(RecipeIdling.getInstance().getIdling());
    }
}