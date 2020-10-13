package com.alfonso.recipes.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.room.Room;

import com.alfonso.recipes.database.AppDataBase;
import com.alfonso.recipes.di.DataBaseModule;
import com.alfonso.recipes.di.WebServiceModule;
import com.alfonso.recipes.repository.IRecipeRepository;
import com.alfonso.recipes.repository.RecipeRepository;

public class RecipesContentProvider extends ContentProvider {
    public static final String URI_AUTHORITIES = "com.alfonso.recipes.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + URI_AUTHORITIES);
    public static final String INGREDIENTS_TABLE = "ingredients";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private IRecipeRepository repository;
    private AppDataBase appDataBase;

    static  {
        uriMatcher.addURI("com.alfonso.recipes.provider","ingredients/#",1);
    }

    public RecipesContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        appDataBase = Room.databaseBuilder(getContext(),AppDataBase.class, DataBaseModule.DATABASE_NAME).allowMainThreadQueries().build();
        repository = new RecipeRepository(getContext(), WebServiceModule.provideRecipeService(),appDataBase);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case 1:
                int id = Integer.parseInt(uri.getLastPathSegment());
                return repository.getIngredientsRaw(id);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
