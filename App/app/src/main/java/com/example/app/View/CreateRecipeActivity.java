package com.example.app.View;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapters.IngredientListAdapter;
import com.example.app.Model.Recipe;
import com.example.app.Model.RecipeWithIngredients;
import com.example.app.R;
import com.example.app.ViewModel.CreateRecipeActivityViewModel;

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

    private void initiate()
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
        viewModel.getIngredients().observe(this,ingredients -> {
            adapter.setItems(ingredients);
        });
        saveRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEditButtons())
                {
                    Context context = getApplicationContext();
                    String text = "Insert Required Fields";
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(context, text, duration).show();
                }
                else
                {
                    System.out.println("Data Inserted");
                    Recipe recipe = new Recipe();
                    recipe.setDescription(recipeDescription.getText().toString());
                    recipe.setTitle(recipeTitle.getText().toString());
                    recipe.setPreparation(recipePreparation.getText().toString());

                    RecipeWithIngredients recipeWithIngredients = new RecipeWithIngredients();
                    recipeWithIngredients.setRecipe(recipe);
                    recipeWithIngredients.setIngredients(viewModel.getIngredients().getValue());
                   // viewModel.insert(recipe);
                    viewModel.insertRecipeWithIngredients(recipeWithIngredients);

                    finish();
                }

            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            int id = bundle.getInt("RecipeID");
            System.out.println("ID IS : " + id);
            viewModel.getRecipe(id).observe(this,recipeWithIngredients -> {
                recipeTitle.setText(recipeWithIngredients.getRecipe().getTitle());
                recipeDescription.setText(recipeWithIngredients.getRecipe().getDescription());
                recipePreparation.setText(recipeWithIngredients.getRecipe().getPreparation());
                viewModel.addIngredientList(recipeWithIngredients.getIngredients());

            });

        }


    }
    private boolean checkEditButtons()
    {
        boolean check = false;
        if(recipePreparation.getText().toString().length()==0)
        {
            check=true;
            recipePreparation.setHintTextColor(getResources().getColor(R.color.red));
            recipePreparation.getBackground().setColorFilter(getResources().getColor(R.color.red),
                    PorterDuff.Mode.SRC_ATOP);
            //recipePreparation.setBackgroundTintList(ColorStateList.valueOf(R.color.budGreen));

        }
        if(recipeDescription.getText().toString().length()==0)
        {
            check=true;
            recipeDescription.setHintTextColor(getResources().getColor(R.color.red));
            recipeDescription.getBackground().setColorFilter(getResources().getColor(R.color.red),
                    PorterDuff.Mode.SRC_ATOP);
        }
        if(recipeTitle.getText().toString().length()==0) {
            check = true;
            recipeTitle.setHintTextColor(getResources().getColor(R.color.red));
            recipeTitle.getBackground().setColorFilter(getResources().getColor(R.color.red),
                    PorterDuff.Mode.SRC_ATOP);
        }

        return check;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        initiate();

    }
}
