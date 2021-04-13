package com.example.app.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.app.Model.ShoppingItem;

@Database(entities = {ShoppingItem.class}, version = 1)
public abstract class ShoppingItemDatabase extends RoomDatabase
{
    private static ShoppingItemDatabase instance;
    public abstract ShoppingItemDAO dao();

    public static synchronized ShoppingItemDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ShoppingItemDatabase.class, "ShoppingItem_Database")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }
}
