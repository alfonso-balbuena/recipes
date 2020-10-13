package com.alfonso.recipes.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "ingredients",foreignKeys = @ForeignKey(entity = Recipe.class,
                                                            parentColumns = "id",
                                                            childColumns = "recipeId",
                                                            onDelete = CASCADE),
        indices = @Index(value = "recipeId"))
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private double quantity;
    @ColumnInfo
    private String measure;
    @SerializedName("ingredient")
    @ColumnInfo
    private String name;
    @ColumnInfo
    private int recipeId;

    public Ingredient() {

    }

    @Ignore
    public Ingredient(double quantity,String measure,String name,int recipeId) {
        this.quantity = quantity;
        this.measure = measure;
        this.name = name;
        this.recipeId = recipeId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
