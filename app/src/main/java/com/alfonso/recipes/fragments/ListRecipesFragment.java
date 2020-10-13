package com.alfonso.recipes.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alfonso.recipes.R;
import com.alfonso.recipes.adapter.RecipesAdapter;
import com.alfonso.recipes.viewModel.ListRecipesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListRecipesFragment extends Fragment {

    private ListRecipesViewModel viewModel;
    private RecipesAdapter adapter;
    private static final String TAG = ListRecipesFragment.class.getName();

    public ListRecipesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(requireActivity()).get(ListRecipesViewModel.class);
        View view = inflater.inflate(R.layout.fragment_list_recipes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_recipes);
        adapter = new RecipesAdapter(model -> viewModel.select(model));
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
        } else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        viewModel.getRecipes().observe(getViewLifecycleOwner(),recipes -> adapter.setRecipes(recipes));
        return view;
    }
}