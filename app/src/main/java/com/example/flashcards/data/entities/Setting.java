package com.example.flashcards.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "settings")
public class Setting {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "night_mode")
    public boolean nightMode;

    public Setting(int id, boolean nightMode) {
        this.id = id;
        this.nightMode = nightMode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}