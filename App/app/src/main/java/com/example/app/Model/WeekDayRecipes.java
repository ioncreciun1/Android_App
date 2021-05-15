package com.example.app.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "WeekDay_Recipe",
primaryKeys = {"WeekDay","FK_ID_recipe"})
public class WeekDayRecipes {

    @NonNull
    private String WeekDay;
    @ForeignKey(entity = Recipe.class, parentColumns = "ID_recipe", childColumns = "FK_ID_recipe")
    private int FK_ID_recipe;

    public String getWeekDay() {
        return WeekDay;
    }

    public void setWeekDay(String weekDay) {
        WeekDay = weekDay;
    }

    public int getFK_ID_recipe() {
        return FK_ID_recipe;
    }

    public void setFK_ID_recipe(int FK_ID_recipe) {
        this.FK_ID_recipe = FK_ID_recipe;
    }

    public WeekDayRecipes() {
    }
}
