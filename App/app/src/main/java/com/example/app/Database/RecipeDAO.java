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
import com.example.app.Model.WeekDayRecipes;

import java.util.List;

@Dao
public abstract class RecipeDAO {
    @Insert
    public abstract long Insert(Recipe item);

    @Insert
    abstract void insertIngredients(List<Ingredient> ingredients);



    @Insert
    public abstract void insertWeekDayRecipes(WeekDayRecipes weekDayRecipe);

    public void insertRecipeAndIngredients(RecipeWithIngredients recipeWithIngredients)
    {
        long id = Insert(recipeWithIngredients.getRecipe());
            recipeWithIngredients.getIngredients().forEach(ingredient -> {
                ingredient.setFK_recipe(id);
            });
        insertIngredients(recipeWithIngredients.getIngredients());

    }

    @Update
    public abstract void UpdateRecipe(Recipe item);

    public  void updateRecipeWithIngredients(RecipeWithIngredients recipeWithIngredients)
    {
        UpdateRecipe(recipeWithIngredients.getRecipe());

        deleteIngredients(recipeWithIngredients.getRecipe().getID_recipe());

        recipeWithIngredients.getIngredients().forEach(ingredient -> {
            ingredient.setFK_recipe(recipeWithIngredients.getRecipe().getID_recipe());
        });

        insertIngredients(recipeWithIngredients.getIngredients());

    }

    @Delete
    public abstract void Delete(Recipe item);


    @Query("DELETE from Recipe where ID_recipe = :id")
    abstract public void deleteRecipeById(int id);

    @Query("DELETE from Ingredient where FK_recipe LIKE :id")
    abstract public void deleteIngredients(int id);


    @Query("Select ID_recipe,title,description from Recipe")
    abstract public LiveData<List<RecipeCard>> getAllRecipeCard();

    @Query("SELECT * FROM Recipe where ID_recipe = :id")
    abstract public Recipe getRecipe(int id);


    @Query("SELECT * FROM Ingredient where FK_recipe = :fk_recipe_id")
    abstract public List<Ingredient> getIngredients(int fk_recipe_id);

    @Transaction
    @Query("SELECT * FROM Recipe,Ingredient where ID_recipe = :id and FK_recipe = :id")
    abstract public LiveData<RecipeWithIngredients> getRecipeWithIngredients(int id);


    @Query("Select r.ID_recipe,r.title,r.description from WeekDay_Recipe as WDR inner join Recipe r on r.ID_recipe = WDR.FK_ID_recipe" +
            " where WDR.WeekDay = :weekDay")
    abstract public LiveData<List<RecipeCard>> getRecipesByWeekDay(String weekDay);

    @Query("DELETE from WeekDay_Recipe where FK_ID_recipe = :id_recipe")
    public abstract void deleteWeekDayRecipe(int id_recipe);

    @Query("DELETE from WeekDay_Recipe where FK_ID_recipe = :id_recipe and WeekDay like :day")
    public abstract void deleteWeekDayRecipe(int id_recipe,String day);

}
