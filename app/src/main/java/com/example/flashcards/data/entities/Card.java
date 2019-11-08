package com.example.flashcards.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "cards", foreignKeys = @ForeignKey(entity = Deck.class,
                                                       parentColumns = "id",
                                                       childColumns = "deck_id"))
public class Card {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "deck_id")
    private int deckId;

    @ColumnInfo(name = "front_side")
    public String frontSide;

    @ColumnInfo(name = "back_side")
    public String backSide;

    @ColumnInfo(name = "date_time_ready")
    private Date dateTimeReady;

    public Card(int id, int deckId, String frontSide, String backSide, Date dateTimeReady) {
        this.id = id;
        this.deckId = deckId;
        this.frontSide = frontSide;
        this.backSide = backSide;
        this.dateTimeReady = dateTimeReady;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public Date getDateTimeReady() {
        return dateTimeReady;
    }

    public void setDateTimeReady(Date dateTimeReady) {
        this.dateTimeReady = dateTimeReady;
    }
}
