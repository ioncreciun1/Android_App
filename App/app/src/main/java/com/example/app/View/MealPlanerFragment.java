package com.example.app.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.app.R;
import com.google.android.material.tabs.TabLayout;


public class MealPlanerFragment extends Fragment {


    private TabLayout tabs;
   private MealPlanerRecipesFragment mealPlanerRecipesFragment;

    private void init(View view)
    {
        mealPlanerRecipesFragment = new MealPlanerRecipesFragment();
        tabs = view.findViewById(R.id.WeekTabLayout);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.MealPlanerRecipesLayout,mealPlanerRecipesFragment).commit();
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mealPlanerRecipesFragment.getRecipesByWeekDay(tab.getText().toString());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_planer, container, false);
        init(view);
        System.out.println("Created Planer");
        return view;
    }
}