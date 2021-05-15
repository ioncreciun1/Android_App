package com.example.app.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.app.Model.Ingredient;
import com.example.app.Model.Recipe;
import com.example.app.Model.WeekDayRecipes;

@Database(entities = {Recipe.class, Ingredient.class, WeekDayRecipes.class}, version = 11)
public abstract class RecipeDatabase extends RoomDatabase {
    private static RecipeDatabase instance;
    public abstract RecipeDAO dao();

    public static synchronized RecipeDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RecipeDatabase.class, "Recipe_Database")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }
}
