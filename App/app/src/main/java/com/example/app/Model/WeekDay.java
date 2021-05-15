package com.example.app.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WeekDay")
public class WeekDay {
    @PrimaryKey()
    private int ID_WeekDay;
    private String dayName;

    public int getID_WeekDay() {
        return ID_WeekDay;
    }

    public void setID_WeekDay(int ID_WeekDay) {
        this.ID_WeekDay = ID_WeekDay;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public WeekDay(int ID_WeekDay, String dayName) {
        this.ID_WeekDay = ID_WeekDay;
        this.dayName = dayName;
    }
}
