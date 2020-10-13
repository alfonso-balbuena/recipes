package com.alfonso.recipes;

import android.content.Intent;
import android.os.Parcelable;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.alfonso.recipes.activity.RecipeDetailActivity;
import com.alfonso.recipes.activity.StepDetailActivity;
import com.alfonso.recipes.models.Step;
import com.alfonso.recipes.repository.MockUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

@HiltAndroidTest
@RunWith(AndroidJUnit4.class)
public class StepDetailActivityInstrumentedTest {
    @Rule(order = 0)
    public HiltAndroidRule rule = new HiltAndroidRule(this);

    @Test
    public void testLaunchDetailActivitySteps() {
        ActivityScenario<StepDetailActivity> activityScenario = ActivityScenario.launch(initIntent());
        onView(withId(R.id.tv_all_description_step)).check(matches(withText("Recipe Introduction")));
        onView(withId(R.id.btn_next_step)).perform(click());
        onView(withId(R.id.tv_all_description_step)).check(matches(withText("1. Preheat the oven to 350F.")));
        onView(withId(R.id.btn_next_step)).perform(click());
        onView(withId(R.id.tv_all_description_step)).check(matches(withText("2. Whisk the graham cracker crumbs, 50 grams (1/4 cup) of sugar")));
        onView(withId(R.id.btn_next_step)).check(matches(not(ViewMatchers.isEnabled())));


    }

    private Intent initIntent() {
        List<Step> stepList = MockUtils.getMockSteps();
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), StepDetailActivity.class);
        intent.putExtra(RecipeDetailActivity.STEP_SELECTED,stepList.get(0));
        intent.putParcelableArrayListExtra(RecipeDetailActivity.LIST_STEP, (ArrayList<? extends Parcelable>) stepList);
        intent.putExtra(RecipeDetailActivity.RECIPE_NAME,"Nutella Pie");
        return intent;
    }
}
