package com.example.flashcards.data.background;

public enum OperationType {
    //Deck operations
    FETCH_ALL_DECKS,
    INSERT_DECK,
    REMOVE_ALL_DECKS,
    REMOVE_DECK,
    UPDATE_DECK,

    //Card operations
    FETCH_ALL_CARDS_IN_DECK,
    FETCH_ALL_CARDS,
    FETCH_CARDS_IN_DECK,
    LOAD_CARD,
    INSERT_CARD,
    REMOVE_ALL_CARDS,
    REMOVE_CARD,
    UPDATE_CARD,
    MARK_DELETED,
    UNMARK_DELETED
}