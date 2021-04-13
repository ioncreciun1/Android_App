package com.example.app.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapters.IngredientListAdapter;
import com.example.app.Model.Ingredient;
import com.example.app.R;

import java.util.ArrayList;
import java.util.List;

public class CreateRecipeActivity extends AppCompatActivity
{
    private Button button ;
    private Toolbar toolbar;
    private RecyclerView ingredientList;
    private IngredientListAdapter adapter;
    private List<Ingredient> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TAG","Created");
        setContentView(R.layout.activity_create_recipe);

        toolbar = findViewById(R.id.createRecipeToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button = findViewById(R.id.add_recipe_ingredient_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngredientDialog dialog = new IngredientDialog();
                dialog.show(getSupportFragmentManager(),"Ingredient Dialog");
            }
        });
        Ingredient ingredient = new Ingredient("1 tsp Sugar");
        Ingredient ingredient1 = new Ingredient("One cup of tea");
        Ingredient ingredient3 = new Ingredient("Sweets");
        ingredientList = findViewById(R.id.ingredient_list_create_recipe);
        ingredientList.hasFixedSize();
        ingredientList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new IngredientListAdapter(this);
        ingredientList.setAdapter(adapter);
        items= new ArrayList<>();
        items.add(ingredient);
        items.add(ingredient1);
        items.add(ingredient3);
        items.add(ingredient);
        items.add(ingredient1);
        items.add(ingredient3);
        items.add(ingredient);
        items.add(ingredient1);
        items.add(ingredient3);
        items.add(ingredient);
        items.add(ingredient1);
        items.add(ingredient3);
        items.add(ingredient);
        items.add(ingredient1);
        items.add(ingredient3);
        adapter.setItems(items);
       // Log.i("TAG","ExECUTED");
    }
}
