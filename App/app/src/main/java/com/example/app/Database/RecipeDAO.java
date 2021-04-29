package com.example.app.Database;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.app.Model.Ingredient;
import com.example.app.Model.Recipe;
import com.example.app.Model.RecipeCard;
import com.example.app.Model.RecipeWithIngredients;

import java.util.List;

@Dao
public abstract class RecipeDAO {
    @Insert
    public abstract long Insert(Recipe item);

    @Insert
    abstract void insertIngredients(List<Ingredient> ingredients);

    @Update
    public abstract void Update(Recipe item);

    @Delete
    public abstract void Delete(Recipe item);

    @Query("Select Id_recipe,title,description from Recipe")
    abstract public LiveData<List<RecipeCard>> getAllRecipeCard();

    @Query("DELETE from Recipe where Id_recipe = :id")
    abstract public void deleteRecipeById(int id);

    public void insertRecipeAndIngredients(RecipeWithIngredients recipeWithIngredients)
    {
        long id = Insert(recipeWithIngredients.getRecipe());
        System.out.println("DAO SIZE: " + recipeWithIngredients.getIngredients().size());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            recipeWithIngredients.getIngredients().forEach(ingredient -> {
                ingredient.setFK_recipe(id);
                System.out.println("ID : " + id);
            });
        }
        insertIngredients(recipeWithIngredients.getIngredients());

    }

    @Transaction
    @Query("SELECT * FROM Recipe,Ingredient where Id_recipe = :id and FK_recipe=:id")
    abstract public LiveData<RecipeWithIngredients> getRecipe(int id);

    @Transaction
    @Query("SELECT * FROM Recipe")
    abstract public LiveData<List<RecipeWithIngredients>> getRecipeWithIngredients();
}
