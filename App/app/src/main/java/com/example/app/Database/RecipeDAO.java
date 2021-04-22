package com.example.app.Database;

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
public interface RecipeDAO {
    @Insert
    void Insert(Recipe item);

    @Update
    void Update(Recipe item);

    @Delete
    void Delete(Recipe item);

    @Query("Select Id_recipe,title,description from Recipe")
    public LiveData<List<RecipeCard>> getAllRecipeCard();

    @Query("DELETE from Recipe where Id_recipe = :id")
    public void deleteRecipeById(int id);

    @Insert
    public void insertRecipeAndIngredients(Recipe recipe,List<Ingredient> ingredients);

    @Transaction
    @Query("SELECT * FROM Recipe where Id_recipe = :id")

    public RecipeWithIngredients getRecipe(int id);

    @Transaction
    @Query("SELECT * FROM Recipe")
    public LiveData<List<RecipeWithIngredients>> getRecipeWithIngredients();
}
