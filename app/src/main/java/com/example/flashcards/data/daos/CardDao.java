package com.example.flashcards.data.daos;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.flashcards.data.entities.Card;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CardDao {
    @Query("SELECT * FROM cards")
    List<Card> getAll();

    @Query("SELECT * FROM cards WHERE id IN (:cardIDs)")
    List<Card> loadAllByIds(int[] cardIDs);

    @Query("SELECT * FROM cards WHERE deck_id = (:deckId) AND is_deleted = 0")
    List<Card> loadAllByDeckId(int deckId);

    @Update
    void update(Card card);

    @Query("UPDATE cards SET is_deleted = 1, deck_id = NULL WHERE id = :id")
    void markDeleted(int id);

    @Query("UPDATE cards SET is_deleted = 0, deck_id = :deckId WHERE id = :id")
    void unmarkDeleted(int id, int deckId);

    @Insert
    void insertAll(Card... cards);

    @Delete
    void delete(Card card);

    @Query("DELETE FROM cards")
    void nukeCards();

    @Query("UPDATE cards SET is_deleted = 1, deck_id = NULL WHERE deck_id = :id")
    void deleteDeck(int id);

    @Query("SELECT * FROM cards WHERE id = :cardId")
    Card getCard(int cardId);

    @Query("SELECT * \n" +
            "FROM cards c\n" +
            "WHERE deck_id = :deckId AND\n" +
            "    date_time_ready <= date('now')" +
            "ORDER BY datetime(date_time_ready) DESC")
    List<Card> getCardsForPractice(int deckId);
}

// TODO: 01-Nov-19 add query that selects cards by the order of date and time for using it with practice activity
