package com.example.app.Repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.app.Database.RecipeDAO;
import com.example.app.Database.RecipeDatabase;
import com.example.app.Model.Ingredient;
import com.example.app.Model.Recipe;
import com.example.app.Model.RecipeWithIngredients;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecipeListRepository {
    private static RecipeListRepository instance;
    private final RecipeDAO dao;

    private final ExecutorService executorService;

    private MutableLiveData<List<Ingredient>> ingredients;

    private RecipeWithIngredients recipe;

    private RecipeListRepository(Application application) {
        RecipeDatabase database = RecipeDatabase.getInstance(application);
        dao = database.dao();
       // items = dao.getRecipeWithIngredients();
        ingredients = new MutableLiveData<>();
        List<Ingredient> newIngredients = new ArrayList<>();
        ingredients.setValue(newIngredients);
        recipe = new RecipeWithIngredients();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized RecipeListRepository getInstance(Application application) {
        if (instance == null)
            instance = new RecipeListRepository(application);

        return instance;
    }
    public LiveData<List<Ingredient>> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient)
    {
        List<Ingredient> list = ingredients.getValue();
        list.add(ingredient);
        ingredients.setValue(list);
    }
    public void clearList()
    {
        List<Ingredient> list = new ArrayList<>();
        ingredients.setValue(
                list
        );
    }

    public RecipeWithIngredients getRecipe() {
        System.out.println(recipe.getRecipe().getTitle());
        return recipe;
    }

    public void insert(Recipe item)
    {
        executorService.execute(()->{
            dao.Insert(item);
        });
    }
    public void insertRecipeWithIngredients(RecipeWithIngredients recipeWithIngredients)
    {
        executorService.execute(()->{
            dao.insertRecipeAndIngredients(recipeWithIngredients);
        });
    }
    public void update(Recipe item)
    {
        executorService.execute(()->{
            dao.UpdateRecipe(item);
        });
    }
    public void remove(Recipe item)
    {
        executorService.execute(()->{
            dao.Delete(item);
        });
    }

    public LiveData<RecipeWithIngredients> getRecipe(int id) {
        return dao.getRecipeWithIngredients(id);
    }

    public void addIngredientList(List<Ingredient> list) {
        ingredients.setValue(list);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateRecipeWithIngredients(RecipeWithIngredients recipeWithIngredients) {
        executorService.execute(()->{
            dao.updateRecipeWithIngredients(recipeWithIngredients);
        });
    }
}
