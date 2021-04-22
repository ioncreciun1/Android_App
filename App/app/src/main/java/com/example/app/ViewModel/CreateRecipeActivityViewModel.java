package com.example.app.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.app.Model.Ingredient;
import com.example.app.Model.Recipe;
import com.example.app.Model.RecipeWithIngredients;
import com.example.app.Repository.RecipeListRepository;

import java.util.List;

public class CreateRecipeActivityViewModel extends AndroidViewModel {
    private final RecipeListRepository repository;

    public CreateRecipeActivityViewModel(Application app) {
        super(app);

        repository = RecipeListRepository.getInstance(app);
    }

    public void addIngredient(Ingredient ingredient)
    {
        repository.addIngredient(ingredient);
    }
    public void clearList()
    {
        repository.clearList();
    }

    public LiveData<List<Ingredient>> getIngredients()
    {
        return repository.getIngredients();
    }
    public void insert(final Recipe item)
    {
        repository.insert(item);
    }

    public void insertRecipeWithIngredients(final Recipe item,List<Ingredient> ingredients)
    {
        repository.insertRecipeWithIngredients(item, ingredients);
    }

    public RecipeWithIngredients getRecipe(int id)
    {
        return repository.getRecipe(id);
    }
    public void update(final Recipe item)
    {
        repository.update(item);
    }
    public void remove(final Recipe item)
    {
        repository.remove(item);
    }
}
