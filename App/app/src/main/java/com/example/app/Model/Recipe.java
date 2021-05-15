package com.example.app.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="Recipe")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    private int ID_recipe;
    private String title;
    private String description;
    private String preparation;

    @Ignore
    public Recipe(int id, String title, String description, String preparation) {
        this.ID_recipe = id;
        this.title = title;
        this.description = description;
        this.preparation = preparation;
    }

    public Recipe() {

    }

    public int getID_recipe() {
        return ID_recipe;
    }

    public void setID_recipe(int id_recipe) {
        this.ID_recipe = id_recipe;
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

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }
}
