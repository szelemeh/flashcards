package com.example.flashcards.data.daos;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.flashcards.data.entities.Card;
import java.util.List;

@Dao
public interface CardDao {
    @Query("SELECT * FROM cards")
    List<Card> getAll();

    @Query("SELECT * FROM cards WHERE id IN (:cardIDs)")
    List<Card> loadAllByIds(int[] cardIDs);

    @Insert
    void insertAll(Card... cards);

    @Delete
    void delete(Card card);
}

// TODO: 01-Nov-19 add query that selects cards by the order of date and time for using it with practice activity
