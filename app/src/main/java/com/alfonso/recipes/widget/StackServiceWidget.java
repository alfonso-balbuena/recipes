package com.alfonso.recipes.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.alfonso.recipes.R;
import com.alfonso.recipes.contentProvider.RecipesContentProvider;

public class StackServiceWidget extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(),intent);
    }
}

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private final Context context;
    private Cursor cursor;
    private final int idRecipe;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        Log.d(StackRemoteViewsFactory.class.getName(),"onConstructor");
        this.context = context;
        idRecipe = intent.getIntExtra(ConfigureWidget.RECIPE_ID_WIDGET,0);
    }

    @Override
    public void onCreate() {
        updateCursor();
    }

    @Override
    public void onDataSetChanged() {
        updateCursor();
    }

    private void updateCursor() {
        Uri INGREDIENTS_URI = RecipesContentProvider.BASE_CONTENT_URI.buildUpon().appendPath(RecipesContentProvider.INGREDIENTS_TABLE)
                .appendPath(Integer.toString(idRecipe)).build();
        if(cursor != null) cursor.close();
        cursor = context.getContentResolver().query(
                INGREDIENTS_URI,null,null,null,null);
    }

    @Override
    public void onDestroy() {
        cursor.close();
    }

    @Override
    public int getCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        if(cursor == null || cursor.getCount() == 0) return null;
        cursor.moveToPosition(i);
        int nameId = cursor.getColumnIndex("name");
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        rv.setTextViewText(R.id.widget_item,cursor.getString(nameId));
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
