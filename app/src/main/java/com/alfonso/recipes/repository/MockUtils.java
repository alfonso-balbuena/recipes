package com.alfonso.recipes.repository;

import com.alfonso.recipes.models.Ingredient;
import com.alfonso.recipes.models.Step;

import java.util.ArrayList;
import java.util.List;

public class MockUtils {
    public static List<Step> getMockSteps() {
        List<Step> stepList = new ArrayList<>();
        stepList.add(new Step(0,"Recipe Introduction","Recipe Introduction","https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4","",0));
        stepList.add(new Step(1,"Starting prep","1. Preheat the oven to 350F.","","",0));
        stepList.add(new Step(2,"Prep the cookie crust.","2. Whisk the graham cracker crumbs, 50 grams (1/4 cup) of sugar","","",0));
        return stepList;
    }

    public static List<Ingredient> getMockIngredients() {
        List<Ingredient> data = new ArrayList<>();
        data.add(new Ingredient(3,"CUP","Graham Cracker crumbs",0));
        data.add(new Ingredient(6,"TBLSP","unsalted butter, melted",0));
        data.add(new Ingredient(.5,"CUP","granulated sugar",0));
        return data;
    }
}
