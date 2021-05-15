package com.example.app.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.app.Model.RecipeWithIngredients;
import com.example.app.Model.ShoppingItem;
import com.example.app.Repository.RecipeListRepository;
import com.example.app.Repository.ShoppingListRepository;

public class RecipeActivityViewModel extends AndroidViewModel  {

    private final RecipeListRepository repository;
    private final ShoppingListRepository shoppingListRepository;

    public RecipeActivityViewModel(@NonNull Application application) {
        super(application);

        repository = RecipeListRepository.getInstance(application);
        shoppingListRepository = ShoppingListRepository.getInstance(application);
    }

    public LiveData<RecipeWithIngredients> getRecipe(int id)
    {

        return repository.getRecipe(id);
    }

    public void addToShoppingList(String name)
    {
        ShoppingItem item = new ShoppingItem(name);
        shoppingListRepository.insert(item);
    }
}
