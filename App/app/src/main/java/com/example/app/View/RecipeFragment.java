package com.example.app.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapters.RecipeCardListAdapter;
import com.example.app.R;
import com.example.app.ViewModel.RecipeFragmentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class RecipeFragment extends Fragment {

    private RecyclerView recipeList;
    private RecipeCardListAdapter adapter;
    private FloatingActionButton recipeFAB;
    private RecipeFragmentViewModel viewModel;



    private void init(View root)
    {

        viewModel = new ViewModelProvider(this).get(RecipeFragmentViewModel.class);
        recipeList = (RecyclerView)root.findViewById(R.id.recipesRecycleView);
        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(root.getContext()));
        adapter = new RecipeCardListAdapter(this);
        recipeList.setAdapter(adapter);

        viewModel.getAllRecipeCards().observe(getViewLifecycleOwner(),recipeCards -> {
            adapter.setItems(recipeCards);
        });


        recipeFAB = root.findViewById(R.id.recipeFAB);
        recipeFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                Class destination = CreateRecipeActivity.class;

                Intent intent = new Intent(context, destination);
                startActivity(intent);
            }
        });


        ItemTouchHelper helper = new ItemTouchHelper(new RecycleItemTouchHelper(adapter));
        helper.attachToRecyclerView(recipeList);
    }

    public void deleteItem(int id)
    {
        viewModel.deleteRecipe(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_recipe, container, false);
        init(root);
        return root;
    }

    public void openWeekDaysDialog(int id)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("Recipe_ID",id);
        WeekDaysDialog dialog = new WeekDaysDialog();
        dialog.setArguments(bundle);
        dialog.show(getParentFragmentManager(),"Add Recipe to WeekDay");
    }

}