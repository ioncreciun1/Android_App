package com.example.app.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(
        tableName="Ingredient"
)
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    private int Id_ingredient;
    private int FK_recipe;

    public int getFK_recipe() {
        return FK_recipe;
    }

    public void setFK_recipe(int FK_recipe) {
        this.FK_recipe = FK_recipe;
    }

    public Ingredient() {
    }
    private String name;

    public Ingredient(int id, String name) {
        this.Id_ingredient = id;
        this.name = name;
    }
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
