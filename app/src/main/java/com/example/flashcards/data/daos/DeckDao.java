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

    @Query("SELECT COUNT(*) FROM cards WHERE deck_id = (:deckId)")
    Integer getTotalCardsNumberInDeck(int deckId);

    @Query("SELECT COUNT(*) FROM decks")
    Integer getDecksNumber();

    @Insert
    void insertAll(Deck... decks);

    @Query("DELETE FROM decks where id=:id")
    void delete(int id);

    @Query("DELETE FROM decks")
    void nukeDecks();

    @Query("UPDATE decks\n SET deck_name = :deckName WHERE id = :id;")
    void renameDeck(String deckName, int id);
}
