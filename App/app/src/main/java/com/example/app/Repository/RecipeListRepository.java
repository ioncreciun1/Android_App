package com.example.app.Repository;

import android.app.Application;

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
    private final LiveData<List<RecipeWithIngredients>> items;
    private final ExecutorService executorService;
    private MutableLiveData<List<Ingredient>> ingredients;
    private RecipeWithIngredients recipe;

    private RecipeListRepository(Application application) {
        RecipeDatabase database = RecipeDatabase.getInstance(application);
        dao = database.dao();
        items = dao.getRecipeWithIngredients();
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

    public LiveData<List<RecipeWithIngredients>> getItems() {
        return items;
    }
    public void insert(Recipe item)
    {
        executorService.execute(()->{
            dao.Insert(item);
        });
    }
    public void insertRecipeWithIngredients(Recipe recipe,List<Ingredient> ingredients)
    {
        executorService.execute(()->{
            dao.insertRecipeAndIngredients(recipe,ingredients);
        });
    }
    public void update(Recipe item)
    {
        executorService.execute(()->{
            dao.Update(item);
        });
    }
    public void remove(Recipe item)
    {
        executorService.execute(()->{
            dao.Delete(item);
        });
    }

    public RecipeWithIngredients getRecipe(int id) {
executorService.execute(()->{
    recipe = dao.getRecipe(id);
});
return recipe;
    }
}
