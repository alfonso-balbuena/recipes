package com.alfonso.recipes.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alfonso.recipes.R;
import com.alfonso.recipes.adapter.IngredientsAdapter;
import com.alfonso.recipes.adapter.StepsAdapter;
import com.alfonso.recipes.viewModel.RecipeDetailViewModel;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class IngredientsStepsFragment extends Fragment {

    private RecipeDetailViewModel viewModel;
    private IngredientsAdapter ingredientsAdapter;
    private StepsAdapter stepsAdapter;
    public IngredientsStepsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients_steps, container, false);
        Log.d(IngredientsStepsFragment.class.getName(),"OnCreateView");
        ingredientsAdapter = new IngredientsAdapter();
        viewModel = new ViewModelProvider(requireActivity()).get(RecipeDetailViewModel.class);
        initRecyclerViews(view);
        viewModel.getIngredients().observe(getViewLifecycleOwner(), ingredients -> ingredientsAdapter.setIngredientList(ingredients));
        viewModel.getSteps().observe(getViewLifecycleOwner(),steps -> stepsAdapter.setStepList(steps));
        return view;
    }

    private void initRecyclerViews(View view) {
        RecyclerView rvIngredients = view.findViewById(R.id.rv_ingredients);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rvIngredients.setHasFixedSize(true);
        rvIngredients.setLayoutManager(linearLayoutManager);
        rvIngredients.setAdapter(ingredientsAdapter);
        RecyclerView rvSteps = view.findViewById(R.id.rv_steps);
        LinearLayoutManager linearLayoutManagerSteps = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        stepsAdapter = new StepsAdapter((model,previous) -> {
            StepsAdapter.StepsAdapterViewHolder viewHolder =  (StepsAdapter.StepsAdapterViewHolder)rvSteps.findViewHolderForAdapterPosition(model.getNumberStep());
            viewHolder.select();
            if(previous >= 0) {
                Log.d("FRAGMENT","Unselect");
                viewHolder =  (StepsAdapter.StepsAdapterViewHolder)rvSteps.findViewHolderForAdapterPosition(previous);
                viewHolder.unSelect();
            }
            viewModel.selectStep(model);
        });
        rvSteps.setHasFixedSize(true);
        rvSteps.setLayoutManager(linearLayoutManagerSteps);
        rvSteps.setAdapter(stepsAdapter);
    }
}