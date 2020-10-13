package com.alfonso.recipes.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.alfonso.recipes.R;

/**
 * Implementation of App Widget functionality.
 */
public class RecipesWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Construct the RemoteViews object
        SharedPreferences pref = context.getSharedPreferences(ConfigureWidget.WIDGET_PREFERENCE,0);
        String nameRecipe = pref.getString(ConfigureWidget.RECIPE_NAME_WIDGET,"");
        int idRecipe = pref.getInt(ConfigureWidget.RECIPE_ID_WIDGET,0);
        RemoteViews views = getView(context,appWidgetId,nameRecipe,idRecipe);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        SharedPreferences pref = context.getSharedPreferences(ConfigureWidget.WIDGET_PREFERENCE,0);
        String nameRecipe = pref.getString(ConfigureWidget.RECIPE_NAME_WIDGET,"");
        int idRecipe = pref.getInt(ConfigureWidget.RECIPE_ID_WIDGET,0);

        for (int appWidgetId : appWidgetIds) {
            RemoteViews rv = getView(context,appWidgetId,nameRecipe,idRecipe);
            appWidgetManager.updateAppWidget(appWidgetId,rv);
        }
    }

    private static RemoteViews getView(Context context,int appWidgetId,String name,int idRecipe) {
        Intent intent = new Intent(context,StackServiceWidget.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
        intent.putExtra(ConfigureWidget.RECIPE_ID_WIDGET,idRecipe);
        RemoteViews rv = new RemoteViews(context.getPackageName(),R.layout.recipes_widget);
        rv.setTextViewText(R.id.name_recipe_widget,"Ingredients for " + name);
        rv.setRemoteAdapter(R.id.stack_view_widget,intent);
        //rv.setEmptyView(R.id.stack_view_widget,R.id.empty_widget);
        return rv;
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

