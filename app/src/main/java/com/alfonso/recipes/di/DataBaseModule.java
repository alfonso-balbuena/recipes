package com.alfonso.recipes.di;

import android.content.Context;

import androidx.room.Room;

import com.alfonso.recipes.database.AppDataBase;


public class DataBaseModule {
    public static final String DATABASE_NAME = "recipesDataBase";

    public static AppDataBase provideAppDataBase(Context context) {
        return Room.databaseBuilder(context,AppDataBase.class,DATABASE_NAME).build();
    }
}
