package com.alfonso.recipes.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfonso.recipes.R;
import com.alfonso.recipes.models.Step;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsAdapterViewHolder> {

    private List<Step> stepList;
    private final IClickHandlerAdapterSelect<Step> clickHandlerAdapter;
    int previousPosition = -1;

    public StepsAdapter(IClickHandlerAdapterSelect<Step> clickHandlerAdapter) {
        this.clickHandlerAdapter = clickHandlerAdapter;
        setHasStableIds(true);
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StepsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutItem = R.layout.step_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutItem,parent,false);

        return new StepsAdapter.StepsAdapterViewHolder(view,model -> {
            clickHandlerAdapter.onClick(model,previousPosition);
            previousPosition = model.getNumberStep();
        }, position -> stepList.get(position)) ;
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapterViewHolder holder, int position) {
        holder.tvShortDescription.setText(stepList.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return (stepList == null) ? 0 : stepList.size();
    }

    public static class StepsAdapterViewHolder extends ViewHolderClick {
        private final TextView tvShortDescription;
        private final View itemView;

        public void select() {
            itemView.setBackgroundColor(Color.GREEN);
        }

        public void unSelect() {
            itemView.setBackgroundColor(Color.WHITE);
        }

        public StepsAdapterViewHolder(@NonNull View itemView, @NonNull IClickHandlerAdapter<Step> clickHandlerAdapter, @NonNull IModelPosition position) {
            super(itemView, clickHandlerAdapter, position);
            tvShortDescription = itemView.findViewById(R.id.tv_description_step);
            this.itemView = itemView;
        }
    }
}
