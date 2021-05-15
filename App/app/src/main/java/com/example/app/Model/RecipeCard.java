package com.example.app.Model;

import androidx.room.PrimaryKey;

public class RecipeCard {
    @PrimaryKey
    private int ID_recipe;
    private String title;
    private String description;

    public RecipeCard(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getID_recipe() {
        return ID_recipe;
    }

    public void setID_recipe(int id_recipe) {
        ID_recipe = id_recipe;
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
