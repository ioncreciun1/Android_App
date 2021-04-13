package com.example.app.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.app.Model.ShoppingItem;

import java.util.List;

@Dao
public interface ShoppingItemDAO
{
    @Insert
    void Insert(ShoppingItem item);

    @Update
    void Update(ShoppingItem item);

    @Delete
    void Delete(ShoppingItem item);

    @Query("Select * from Shopping_Item")
    LiveData<List<ShoppingItem>> getAllShoppingItems();
}
