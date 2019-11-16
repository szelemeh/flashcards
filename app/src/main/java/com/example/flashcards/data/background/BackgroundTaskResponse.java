package com.example.flashcards.data.background;

import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;

import java.util.ArrayList;
import java.util.List;

public interface BackgroundTaskResponse {
    void deliverDecks(ArrayList<Deck> dreshDecks);
    void deliverCards(ArrayList<Card> freshCards);
}
