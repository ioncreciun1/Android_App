package com.example.app.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.app.Model.ShoppingItem;
import com.example.app.Repository.ShoppingListRepository;

import java.util.List;

public class ShoppingListFragmentViewModel extends AndroidViewModel {

    private final ShoppingListRepository repository;

    public ShoppingListFragmentViewModel(Application app) {
        super(app);
        repository = ShoppingListRepository.getInstance(app);
    }

    public LiveData<List<ShoppingItem>> getAllShoppingItems()
    {
        return repository.getItems();
    }

    public void insert(final ShoppingItem item)
    {
        repository.insert(item);
    }

    public void update(final ShoppingItem item)
    {
        repository.update(item);
    }

    public void remove(final ShoppingItem item)
    {
        repository.remove(item);
    }

    public void updateStatus(int status,int id)
    {
        repository.updateStatus(status, id);
    }
}
