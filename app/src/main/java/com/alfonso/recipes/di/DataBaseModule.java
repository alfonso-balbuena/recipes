package com.alfonso.recipes.di;

import android.content.Context;

import androidx.room.Room;

import com.alfonso.recipes.database.AppDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;


@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {
    public static final String DATABASE_NAME = "recipesDataBase";

    @Singleton
    @Provides
    public static AppDataBase provideAppDataBase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context,AppDataBase.class,DATABASE_NAME).build();
    }
}
