package com.example.app.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.app.Model.RecipeCard;
import com.example.app.Repository.RecipeCardListRepository;

import java.util.List;

public class RecipeFragmentViewModel extends AndroidViewModel {
    private final RecipeCardListRepository repository;

    public RecipeFragmentViewModel(Application app) {
        super(app);
        repository = RecipeCardListRepository.getInstance(app);
    }

    public LiveData<List<RecipeCard>> getAllRecipeCards()
    {
        return repository.getItems();
    }
    public void deleteRecipe(int id)
    {
        repository.deleteItem(id);
    }

}
