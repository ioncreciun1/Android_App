package com.example.app;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                                selectedFragment = new WeekDaysFragment();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,selectedFragment).commit();
                        return true;
                    }
                };
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }


    public void fabClick(View view)
    {
    ShoppingDialog dialog = new ShoppingDialog();
    dialog.show(getSupportFragmentManager(),"example dialog");
    }
}