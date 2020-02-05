package com.example.flashcards.data;

import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;

import java.util.ArrayList;

public interface DatabaseManagerInterface {

    void addDeck(String deckName);
    void updateDeck(Deck updatedDeck);
    void deleteDeck(int deckId);

    Deck getDeck(int deckId);
    void loadAllDecks();


    void addCard(Card newCard);
    void updateCard(Card updatedCard);
    void deleteCard(Card card);

    Card getCard(int cardId);
    void loadAllCards();
    void loadAllCardsInDeck(int deckId);
}
