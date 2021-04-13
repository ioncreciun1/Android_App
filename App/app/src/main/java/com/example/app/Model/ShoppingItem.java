package com.example.app.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="Shopping_Item")
public class ShoppingItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int status;
    private String name;

    @Ignore
    public ShoppingItem(String name) {
        this.name = name;
        this.status=0;
    }

    public ShoppingItem(int id, int status, String name) {
        this.id = id;
        this.status = status;
        this.name = name;
    }

    @Ignore
    public ShoppingItem(int id, String name) {
        this.id = id;
        this.name = name;
        this.status=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
