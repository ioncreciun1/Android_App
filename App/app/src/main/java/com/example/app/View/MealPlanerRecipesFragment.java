package com.example.app.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapters.MealPlanerRecipeCardListAdapter;
import com.example.app.Model.RecipeCard;
import com.example.app.R;
import com.example.app.ViewModel.MealPlanerRecipesFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class MealPlanerRecipesFragment  extends Fragment {
    private RecyclerView recyclerView;
    private MealPlanerRecipeCardListAdapter adapter;
    private List<RecipeCard> list;
    private MealPlanerRecipesFragmentViewModel viewModel;
    private String day  = "Monday";
    public MealPlanerRecipesFragment()
    {

    }

    public void getRecipesByWeekDay(String day)
    {
        viewModel.getRecipesByWeekDayID(day).observe(this,recipeCards -> {
            adapter.setItems(recipeCards);
        });
        this.day = day;
    }


    private void init(View view)
    {
        list = new ArrayList<>();
        list.clear();
        viewModel = new ViewModelProvider(this).get(MealPlanerRecipesFragmentViewModel.class);
        viewModel.getRecipesByWeekDayID("Monday").observe(getViewLifecycleOwner(),recipeCards -> {
            adapter.setItems(recipeCards);
        });
        adapter = new MealPlanerRecipeCardListAdapter(this);
        recyclerView = view.findViewById(R.id.weekDaysRecipesRecycleView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(new MealPlanerRecycleItemTouchHelper(adapter));
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_recipe_card_list, container, false);
        init(root);
        return root;
    }

    public void deleteItem(int id_recipe)
    {
        viewModel.deleteRecipe(id_recipe,day);
    }
}
