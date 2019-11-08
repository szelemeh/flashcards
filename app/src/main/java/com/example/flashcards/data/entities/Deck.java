package com.example.flashcards.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "decks")
public class Deck {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "deck_name")
    public String deckName;

    public Deck(int id, String deckName) {
        this.id = id;
        this.deckName = deckName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}