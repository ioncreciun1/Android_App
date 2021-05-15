package com.example.app.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapters.IngredientListAdapter;
import com.example.app.Model.Recipe;
import com.example.app.Model.RecipeWithIngredients;
import com.example.app.R;
import com.example.app.ViewModel.CreateRecipeActivityViewModel;

import java.util.Objects;

public class CreateRecipeActivity extends AppCompatActivity
{
    private Button IngredientAddButton ;
    private Toolbar toolbar;
    private RecyclerView ingredientList;
    private IngredientListAdapter adapter;
    private Button saveRecipeButton;
    private EditText recipeTitle;
    private EditText recipeDescription;
    private EditText recipePreparation;
    private CreateRecipeActivityViewModel viewModel;
    private Bundle bundle;
    private int recipeID = 0;

    private void initiateVariables()
    {
        viewModel = new ViewModelProvider(this).get(CreateRecipeActivityViewModel.class);
        viewModel.clearList();
        toolbar = findViewById(R.id.createRecipeToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recipeTitle = findViewById(R.id.createRecipeTitle);
        recipeDescription = findViewById(R.id.createRecipeDescription);
        recipePreparation = findViewById(R.id.createRecipePreparation);
        saveRecipeButton = findViewById(R.id.createRecipeSaveButton);
        IngredientAddButton = findViewById(R.id.add_recipe_ingredient_button);

        IngredientAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngredientDialog dialog = new IngredientDialog();
                dialog.show(getSupportFragmentManager(),"Ingredient Dialog");
            }
        });

        ingredientList = findViewById(R.id.ingredient_list_create_recipe);
        ingredientList.hasFixedSize();
        ingredientList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new IngredientListAdapter(this);
        ingredientList.setAdapter(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(new IngredientsCreateRecycleItemTouchHelper(adapter));
        helper.attachToRecyclerView(ingredientList);
    }

    @SuppressLint("SetTextI18n")
    private void initiate()
    {
        initiateVariables();

        viewModel.getIngredients().observe(this,ingredients -> {
            adapter.setItems(ingredients);
        });
        RecipeWithIngredients recipeWithIngredients = new RecipeWithIngredients();
        viewModel.getIngredients().observe(this,
                recipeWithIngredients::setIngredients);

       bundle = getIntent().getExtras();

        if(bundle!=null)
        {
                recipeID = bundle.getInt("RecipeID");
                saveRecipeButton.setText("Edit Recipe");
                viewModel.getRecipe(recipeID).observe(this,setRecipe -> {
                recipeTitle.setText(setRecipe.getRecipe().getTitle());
                recipeDescription.setText(setRecipe.getRecipe().getDescription());
                recipePreparation.setText(setRecipe.getRecipe().getPreparation());
                viewModel.addIngredientList(setRecipe.getIngredients());

            });

        }

        saveRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe recipe = new Recipe();
                recipe.setDescription(recipeDescription.getText().toString());
                recipe.setTitle(recipeTitle.getText().toString());
                recipe.setPreparation(recipePreparation.getText().toString());


                recipeWithIngredients.setRecipe(recipe);
                recipeWithIngredients.setIngredients(viewModel.getIngredients().getValue());
                if(checkEditButtons())
                {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, "Insert Required Fields", duration).show();
                }
                else{
                    if (bundle != null) {
                        recipeWithIngredients.getRecipe().setID_recipe(recipeID);
                        viewModel.updateRecipeWithIngredients(recipeWithIngredients);
                        finish();

                    } else {

                        // viewModel.insert(recipe);
                        viewModel.insertRecipeWithIngredients(recipeWithIngredients);

                        finish();
                    }
                }

            }
        });
    }
    @SuppressLint("ResourceAsColor")
    private boolean checkEditButtons()
    {
        boolean check = false;
        TextView  Title = findViewById(R.id.createRecipeNameLabel);;
        TextView Ingredient = findViewById(R.id.createRecipeIngredientsLabel);;
        TextView Description = findViewById(R.id.createRecipeDescriptionLabel);
        TextView Preparation = findViewById(R.id.createRecipePreparationLabel);

        Title.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        Ingredient.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        Description.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        Preparation.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        if(recipeTitle.getText().toString().length()==0) {

            check = true;
            Title.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
        }
        if(Objects.requireNonNull(viewModel.getIngredients().getValue()).size()==0)
        {
            check = true;
            Ingredient.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
        }

        if(recipeDescription.getText().toString().length()==0)
        {
            check=true;
            Description.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
        }
        if(recipePreparation.getText().toString().length()==0)
        {
            check=true;
            Preparation.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));

        }
        return check;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        initiate();

    }

    public void deleteIngredient(int position)
    {
        viewModel.deleteIngredient(position);

    }
}
