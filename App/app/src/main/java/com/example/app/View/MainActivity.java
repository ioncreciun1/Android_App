package com.example.app.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import android.view.MenuItem;


import com.example.app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Enable Only Light Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        initViews();
        setNavigation();
    }

    private void initViews() {

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);



    }

    private void setNavigation()
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new RecipeFragment()).commit();
         BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;

                        switch (item.getItemId())
                        {
                            case R.id.nav_recipes:
                                selectedFragment = new RecipeFragment();
                                break;
                            case R.id.nav_shopping_list:
                                selectedFragment = new ShoppingListFragment();
                                break;
                            case R.id.nav_weekDays:
                                selectedFragment = new MealPlanerFragment();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,selectedFragment).commit();
                        return true;
                    }
                };
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }
}