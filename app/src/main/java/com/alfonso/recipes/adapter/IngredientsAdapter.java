package com.alfonso.recipes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfonso.recipes.R;
import com.alfonso.recipes.models.Ingredient;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsAdapterViewHolder> {

    private List<Ingredient> ingredientList;

    public void setIngredientList(List<Ingredient> list) {
        ingredientList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutItem = R.layout.ingredient_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutItem,parent,false);
        return new IngredientsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapterViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.tvQuantity.setText(Double.toString(ingredient.getQuantity()));
        holder.tvMeasure.setText(ingredient.getMeasure());
        holder.tvIngredient.setText(ingredient.getName());
    }

    @Override
    public int getItemCount() {
        return (ingredientList == null) ? 0 : ingredientList.size();
    }

    public static class IngredientsAdapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvIngredient;
        private final TextView tvMeasure;
        private final TextView tvQuantity;

        public IngredientsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIngredient = itemView.findViewById(R.id.tv_ingredient);
            tvMeasure = itemView.findViewById(R.id.tv_measure);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
        }
    }
}
