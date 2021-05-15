package com.example.app.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.app.Database.RecipeDAO;
import com.example.app.Database.RecipeDatabase;
import com.example.app.Model.RecipeCard;
import com.example.app.Model.WeekDayRecipes;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecipeCardListRepository {
    private static RecipeCardListRepository instance;

    private final RecipeDAO dao;

    private final LiveData<List<RecipeCard>> items;

    private final ExecutorService executorService;

    private RecipeCardListRepository(Application application) {
        RecipeDatabase database = RecipeDatabase.getInstance(application);
        dao = database.dao();
        items = dao.getAllRecipeCard();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized RecipeCardListRepository getInstance(Application application) {
        if (instance == null)
            instance = new RecipeCardListRepository(application);
        return instance;
    }

    public LiveData<List<RecipeCard>> getRecipesByWeekDay(String weekDay)
    {
        return dao.getRecipesByWeekDay(weekDay);
    }


    public LiveData<List<RecipeCard>> getItems() {
        return items;
    }
    public void deleteItem(int id)
    {
        executorService.execute(()->{
            dao.deleteRecipeById(id);
        });
    }


    public void insertWeekDayRecipe(WeekDayRecipes weekDayRecipes) {
        executorService.execute(()->{
            dao.insertWeekDayRecipes(weekDayRecipes);
        });
    }

    public void deleteWeekDayRecipe(int id_recipe) {
        executorService.execute(()->{
            dao.deleteWeekDayRecipe(id_recipe);
        });
    }
}
