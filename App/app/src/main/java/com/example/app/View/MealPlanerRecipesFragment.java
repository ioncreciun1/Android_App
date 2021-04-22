package com.example.app.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapters.RecipeCardListAdapter;
import com.example.app.Model.RecipeCard;
import com.example.app.R;

import java.util.ArrayList;
import java.util.List;

public class MealPlanerRecipesFragment  extends Fragment {
    private RecyclerView recyclerView;
    private RecipeCardListAdapter adapter;
    private List<RecipeCard> list;
    public MealPlanerRecipesFragment()
    {

    }

    public void getRecipesByWeekDay(String day)
    {
        switch (day)
        {
            case "Monday":
                list.clear();
                list.add(new RecipeCard("Monday","NOW its Monday"));
                adapter.setItems(list);
                break;
            case "Tuesday":
                list.clear();
                list.add(new RecipeCard("Tuesday","NOW its Tuesday"));
                adapter.setItems(list);
                System.out.println(adapter.getItems().size());
                System.out.println(recyclerView.getTranslationX());

                break;
            case "Wednesday": ; break;
            case "Thursday": ; break;
            case "Friday": ; break;
            case "Saturday": ; break;
            case "Sunday": ; break;
        }
    }


    private void init(View view)
    {
        list = new ArrayList<>();
        list.clear();
        list.add(new RecipeCard("Monday","NOW its Monday"));

        adapter = new RecipeCardListAdapter(this);
        adapter.setItems(list);
        recyclerView = view.findViewById(R.id.weekDaysRecipesRecycleView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_recipe_card_list, container, false);
        init(root);
        return root;
    }
}
