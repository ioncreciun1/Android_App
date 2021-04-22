package com.example.app.Model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;


public class RecipeWithIngredients {

    @Embedded
    private Recipe recipe;

    @Relation(
            parentColumn = "Id_recipe",
            entityColumn = "FK_recipe"
    )

    private List<Ingredient> ingredients;

    public RecipeWithIngredients() {
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
