package com.example.flashcards.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.flashcards.data.entities.Deck;

import java.util.List;

@Dao
public interface DeckDao {
    @Query("SELECT * FROM decks")
    List<Deck> getAll();

    @Query("SELECT * FROM decks WHERE id IN (:deckIDs)")
    List<Deck> loadAllByIds(int[] deckIDs);

    @Query("SELECT * FROM decks WHERE deck_name LIKE :search ")
    List<Deck> findDeckWithName(String search);

    @Insert
    void insertAll(Deck... decks);

    @Delete
    void delete(Deck deck);
}
