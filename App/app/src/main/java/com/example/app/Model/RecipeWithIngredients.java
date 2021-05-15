package com.example.app.Model;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;


public class RecipeWithIngredients {

    @Embedded
    private Recipe recipe;

    @Relation(
            parentColumn = "ID_recipe",
            entityColumn = "FK_recipe",
            entity = Ingredient.class
    )

    private List<Ingredient> ingredients;

    @Ignore
    public RecipeWithIngredients() {
        ingredients = new ArrayList<>();
    }

    public RecipeWithIngredients(Recipe recipe, List<Ingredient> ingredients) {
        this.recipe = recipe;
        if(ingredients.size()==0)
        {
            this.ingredients = new ArrayList<>();
        }
        else
        {
            this.ingredients = ingredients;
        }

    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
