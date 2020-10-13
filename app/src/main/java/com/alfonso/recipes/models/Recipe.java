package com.alfonso.recipes.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe")
public class Recipe {

    @PrimaryKey
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private int servings;
    @ColumnInfo
    private String image;
    @ColumnInfo
    private int numberOfSteps;
    @ColumnInfo
    private int numberOfIngredients;

    public Recipe() {}
    @Ignore
    public Recipe(int id,String name,int servings, String image,int numberOfIngredients, int numberOfSteps) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
        this.numberOfIngredients = numberOfIngredients;
        this.numberOfSteps = numberOfSteps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public int getNumberOfIngredients() {
        return numberOfIngredients;
    }

    public void setNumberOfIngredients(int numberOfIngredients) {
        this.numberOfIngredients = numberOfIngredients;
    }
}
