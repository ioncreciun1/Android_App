package com.example.app.View;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapters.IngredientListAdapter;
import com.example.app.R;
import com.example.app.ViewModel.RecipeActivityViewModel;

public class RecipeActivity extends AppCompatActivity
{
    private RecipeActivityViewModel viewModel;
    private TextView name;
    private TextView description;
    private TextView preparation;
    private RecyclerView ingredients;
    private IngredientListAdapter adapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initiate();
        setData();
    }

    private void setData()
    {

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("RecipeID");
        viewModel.getRecipe(id).observe(this,recipeWithIngredients -> {
           adapter.setItems(recipeWithIngredients.getIngredients());
            ingredients.setAdapter(adapter);
           // name.setText(recipeWithIngredients.getRecipe().getTitle());
            preparation.setText(recipeWithIngredients.getRecipe().getPreparation());
            description.setText(recipeWithIngredients.getRecipe().getDescription());
            toolbar.setTitle(recipeWithIngredients.getRecipe().getTitle());

        });
    }

    private void initiate()
    {
        viewModel = new ViewModelProvider(this).get(RecipeActivityViewModel.class);
        adapter = new IngredientListAdapter(this);
        description = findViewById(R.id.recipeDescription);
        preparation = findViewById(R.id.recipePreparation);
        ingredients = findViewById(R.id.ingredient_lists_recipe);
        ingredients.hasFixedSize();
        ingredients.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new IngredientsViewRecipeRecycleItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(ingredients);

    }

    public void addToShoppingList(String name) {
        viewModel.addToShoppingList(name);
    }
}
