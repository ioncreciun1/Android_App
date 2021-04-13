package com.example.app.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.app.Database.ShoppingItemDAO;
import com.example.app.Database.ShoppingItemDatabase;
import com.example.app.Model.ShoppingItem;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShoppingListRepository {
    private static ShoppingListRepository instance;
    private final ShoppingItemDAO dao;
    private final LiveData<List<ShoppingItem>> items;
    private final ExecutorService executorService;

    private ShoppingListRepository(Application application) {
        ShoppingItemDatabase database = ShoppingItemDatabase.getInstance(application);
        dao = database.dao();
        items = dao.getAllShoppingItems();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized ShoppingListRepository getInstance(Application application) {
        if (instance == null)
            instance = new ShoppingListRepository(application);

        return instance;
    }

    public LiveData<List<ShoppingItem>> getItems() {
        return items;
    }
    public void insert(ShoppingItem item)
    {
        executorService.execute(()->{
            dao.Insert(item);
        });
    }
    public void update(ShoppingItem item)
    {
        executorService.execute(()->{
            dao.Update(item);
        });
    }
    public void remove(ShoppingItem item)
    {
        executorService.execute(()->{
            dao.Delete(item);
        });
    }
}
