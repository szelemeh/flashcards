package com.example.flashcards.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "decks",
        indices = {
        @Index(value = {"id"})
        })
public class Deck {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "deck_name")
    public String deckName;

    public Deck(String deckName) {
        this.deckName = deckName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}