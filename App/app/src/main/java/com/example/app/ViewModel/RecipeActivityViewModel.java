package com.example.app.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.app.Model.RecipeWithIngredients;
import com.example.app.Repository.RecipeListRepository;

public class RecipeActivityViewModel extends AndroidViewModel  {

    private final RecipeListRepository repository;

    public RecipeActivityViewModel(@NonNull Application application) {
        super(application);

        repository = RecipeListRepository.getInstance(application);
    }

    public LiveData<RecipeWithIngredients> getRecipe(int id)
    {

        return repository.getRecipe(id);
    }
}
