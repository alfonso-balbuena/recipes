package com.alfonso.recipes;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.alfonso.recipes.idling.RecipeIdling;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@HiltAndroidTest
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule(order = 0)
    public HiltAndroidRule rule = new HiltAndroidRule(this);
    @Rule(order = 1)
    public ActivityScenarioRule ruleActivity = new ActivityScenarioRule(MainActivity.class);



    @Before
    public void registerIdlingResource() {
        Intents.init();
        IdlingRegistry.getInstance().register(RecipeIdling.getInstance().getIdling());
    }

    @Test
    public void checkRecyclerView() {
        onView(withText("Nutella Pie")).check(matches(isDisplayed()));
    }

    @Test
    public void clickItemIntent() {
        onView(withId(R.id.rv_recipes)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        Intents.intended(IntentMatchers.hasExtra(MainActivity.RECIPE_NAME,"Nutella Pie"));
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(RecipeIdling.getInstance().getIdling());
        Intents.release();
    }
}