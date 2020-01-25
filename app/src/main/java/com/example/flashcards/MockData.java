package com.example.flashcards;

import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;

import java.util.ArrayList;
import java.util.Date;

public class MockData {
    public static boolean filled = false;
    public static ArrayList<Deck> decks = new ArrayList<>();
    public static ArrayList<Card> cards = new ArrayList<>();

    public static void fillAll() {
        filled = true;
        //filling decks
        decks.add(new Deck("English"));
        decks.add(new Deck("Math"));
        decks.add(new Deck("History"));
        decks.add(new Deck("Biology"));
        decks.add(new Deck("Chemistry"));
        decks.add(new Deck("Law"));
        decks.add(new Deck("Geography"));
        decks.add(new Deck("Literature"));
        decks.add(new Deck("Self-improvement"));
        decks.add(new Deck("Spanish"));

        int i=0;
        for (Deck deck: decks) {
            deck.setId(i++);
        }

        //filling cards
        cards.add(new Card("Question1", "Answer1",1 , new Date()));
        cards.add(new Card("Question2", "Answer2",1 , new Date()));
        cards.add(new Card("Question3", "Answer3",1, new Date()));
        cards.add(new Card("Question4", "Answer4",2 , new Date()));
        cards.add(new Card("Question5", "Answer5",2 , new Date()));
        cards.add(new Card("Question6", "Answer6",2 , new Date()));
        cards.add(new Card("Question7", "Answer7",3 , new Date()));
        cards.add(new Card("Question8", "Answer8",3, new Date()));
        cards.add(new Card("Question9", "Answer9",3, new Date()));
        cards.add(new Card("Question10", "Answer10",4 , new Date()));
    }

    public static ArrayList<Card> getAllCards() {
        if(!filled) fillAll();
        return cards;
    }

    public static ArrayList<Card> getCardsFromDeck(int deckId) {
        if(!filled) fillAll();
        ArrayList<Card> result = new ArrayList<>();
        for (Card card: cards)if(card.getDeckId() == deckId)result.add(card);
        return result;
    }

    public static ArrayList<Deck> getAllDecks() {
        if(!filled) fillAll();
        return decks;
    }

    public static void addDeck(Deck deck) {
        decks.add(deck);
    }
}
