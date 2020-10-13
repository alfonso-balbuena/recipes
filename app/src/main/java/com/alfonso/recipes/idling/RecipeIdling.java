package com.alfonso.recipes.idling;

import androidx.test.espresso.idling.CountingIdlingResource;

public class RecipeIdling {

    private static RecipeIdling reference = null;
    private CountingIdlingResource idling = null;

    private RecipeIdling() {
        idling = new CountingIdlingResource(RecipeIdling.class.getSimpleName());
    }

    public static RecipeIdling getInstance() {
        if (reference == null) {
            reference = new RecipeIdling();
        }
        return reference;
    }

    public CountingIdlingResource getIdling() {
        return idling;
    }
}
