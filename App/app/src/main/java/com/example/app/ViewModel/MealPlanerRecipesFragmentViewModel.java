package com.example.app.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.app.Model.RecipeCard;
import com.example.app.Model.WeekDayRecipes;
import com.example.app.Repository.RecipeCardListRepository;

import java.util.List;

public class MealPlanerRecipesFragmentViewModel extends AndroidViewModel {

    private final RecipeCardListRepository repository;

    public MealPlanerRecipesFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = RecipeCardListRepository.getInstance(application);
    }
    public void insertWeekDayRecipe(WeekDayRecipes weekDayRecipes)
    {
        repository.insertWeekDayRecipe(weekDayRecipes);
    }
    public LiveData<List<RecipeCard>> getRecipesByWeekDayID(String weekDay)
    {
        return repository.getRecipesByWeekDay(weekDay);
    }

    public void deleteRecipe(int id_recipe,String day)
    {
        repository.deleteWeekDayRecipe(id_recipe,day);
    }
}
