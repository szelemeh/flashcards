package com.example.flashcards.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName="cards",
        foreignKeys = @ForeignKey(entity = Deck.class, parentColumns = "id", childColumns = "deck_id"),
        indices = {@Index(value="deck_id")})
public class Card {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="deck_id")
    private Integer deckId;

    @ColumnInfo(name="front_side")
    public String frontSide;

    @ColumnInfo(name="back_side")
    public String backSide;

    @ColumnInfo(name="date_time_ready")
    private Date dateTimeReady;

    @ColumnInfo(name="date_time_created")
    private Date dateTimeCreated;

    @ColumnInfo(name="is_deleted", defaultValue="false")
    private boolean isDeleted;

    @ColumnInfo(name="easiness", defaultValue="2.5")
    private float easiness;

    @ColumnInfo(name="interval", defaultValue="1")
    private int interval;

    @ColumnInfo(name="repetitions", defaultValue="0")
    private int repetitions;

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

    public Integer getDeckId() {
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

    public String getFront() {
        return frontSide;
    }

    public void setFront(String frontSide) {
        this.frontSide = frontSide;
    }

    public String getBack() {
        return backSide;
    }

    public void setBack(String backSide) {
        this.backSide = backSide;
    }

    public float getEasiness() {
        return easiness;
    }

    public void setEasiness(float easiness) {
        this.easiness = easiness;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}
