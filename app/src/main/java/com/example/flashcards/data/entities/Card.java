package com.example.flashcards.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "cards",
        foreignKeys = @ForeignKey(entity = Deck.class, parentColumns = "id", childColumns = "deck_id"),
        indices = {@Index(value = "deck_id")})
public class Card {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "deck_id")
    private int deckId;

    @ColumnInfo(name = "front_side")
    public String frontSide;

    @ColumnInfo(name = "back_side")
    public String backSide;

    @ColumnInfo(name = "date_time_ready")
    private Date dateTimeReady;

    @ColumnInfo(name = "date_time_created")
    private Date dateTimeCreated;


    @ColumnInfo(name = "is_deleted", defaultValue = "false")
    private boolean isDeleted;

    public Card(String frontSide, String backSide, int deckId, Date dateTimeReady) {
        this.deckId = deckId;
        this.frontSide = frontSide;
        this.backSide = backSide;
        this.dateTimeReady = dateTimeReady;
        this.dateTimeCreated = new Date();
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void toggleDeleteState() {
        isDeleted = !isDeleted;
    }

    public Date getDateTimeCreated() {
        return dateTimeCreated;
    }

    public void setDateTimeCreated(Date dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
