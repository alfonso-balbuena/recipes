package com.alfonso.recipes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfonso.recipes.R;
import com.alfonso.recipes.models.Recipe;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesAdapterViewHolder> {
    private List<Recipe> recipeList;
    private final IClickHandlerAdapter<Recipe> clickHandler;

    public RecipesAdapter(IClickHandlerAdapter<Recipe> clickHandler) {
        this.clickHandler = clickHandler;
    }

    public void setRecipes(List<Recipe> data) {
        recipeList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutItem = R.layout.recipe_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutItem,parent,false);
        return new RecipesAdapterViewHolder(view,clickHandler,position -> recipeList.get(position)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesAdapterViewHolder holder, int position) {
        String numberServings = "Servings: ";
        String numberSteps = "Number of steps: ";
        String numberIngredients = "Number of ingredients: ";
        holder.tvIngredients.setText(numberIngredients.concat(Integer.toString(recipeList.get(position).getNumberOfIngredients())));
        holder.tvSteps.setText(numberSteps.concat(Integer.toString(recipeList.get(position).getNumberOfSteps())));
        holder.tvServings.setText(numberServings.concat(Integer.toString(recipeList.get(position).getServings())));
        holder.tvName.setText(recipeList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return (recipeList == null) ? 0 : recipeList.size();
    }

    public static class RecipesAdapterViewHolder extends ViewHolderClick<Recipe> {
        private final TextView tvName;
        private final TextView tvServings;
        private final TextView tvIngredients;
        private final TextView tvSteps;
        public RecipesAdapterViewHolder(@NonNull View itemView, @NonNull IClickHandlerAdapter<Recipe> clickHandlerAdapter, @NonNull IModelPosition<Recipe> position) {
            super(itemView,clickHandlerAdapter,position);
            tvName = itemView.findViewById(R.id.tv_recipe_name);
            tvServings = itemView.findViewById(R.id.tv_recipe_servings);
            tvIngredients = itemView.findViewById(R.id.tv_recipe_ingredients);
            tvSteps = itemView.findViewById(R.id.tv_recipe_steps);
        }
    }
}
