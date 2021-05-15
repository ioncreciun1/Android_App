package com.example.app.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        tableName="Ingredient"
)
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    private int Id_ingredient;

    private long FK_recipe;

    public long getFK_recipe() {
        return FK_recipe;
    }

    public void setFK_recipe(long FK_recipe) {
        this.FK_recipe = FK_recipe;
    }


    private String name;

    public Ingredient()
    {

    }
    @Ignore
    public Ingredient(int id, String name) {
        this.Id_ingredient = id;
        this.name = name;
    }

    @Ignore
    public Ingredient(String name) {
        this.name = name;
    }


    public int getId_ingredient() {
        return Id_ingredient;
    }

    public void setId_ingredient(int id_ingredient) {
        Id_ingredient = id_ingredient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
