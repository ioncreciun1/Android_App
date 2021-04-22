package com.example.app.Model;

import androidx.room.PrimaryKey;

public class RecipeCard {
    @PrimaryKey
    private int Id_recipe;
    private String title;
    private String description;

    public RecipeCard(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId_recipe() {
        return Id_recipe;
    }

    public void setId_recipe(int id_recipe) {
        Id_recipe = id_recipe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
