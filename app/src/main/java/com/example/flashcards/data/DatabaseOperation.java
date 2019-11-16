package com.example.flashcards.data;

import android.content.Context;

import com.example.flashcards.data.adapters.CardAdapter;
import com.example.flashcards.data.adapters.DeckAdapter;
import com.example.flashcards.data.background.BackgroundCardTransaction;
import com.example.flashcards.data.background.BackgroundDeckTransaction;
import com.example.flashcards.data.background.BackgroundTaskDetails;
import com.example.flashcards.data.background.BackgroundTaskResponse;
import com.example.flashcards.data.background.OperationType;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;

import java.util.ArrayList;
import java.util.Date;

public class DatabaseOperation implements BackgroundTaskResponse {
    private DeckAdapter deckAdapter;
    private CardAdapter cardAdapter;
    private Context context;

    public DatabaseOperation(Context context, DeckAdapter deckAdapter){
        this.deckAdapter = deckAdapter;
        this.context = context;
    }

    public DatabaseOperation(Context context, CardAdapter cardAdapter){
        this.cardAdapter = cardAdapter;
        this.context = context;
    }

    //Deck operations

    public void fetchAllDecks() {
        BackgroundDeckTransaction bgTask = new BackgroundDeckTransaction(context, deckAdapter);
        bgTask.delegate = this;
        bgTask.execute(BackgroundTaskDetails.fetchingDecks(OperationType.FETCH_ALL_DECKS));
    }


    public void insertDeck(String deckName) {
        BackgroundDeckTransaction bgTask = new BackgroundDeckTransaction(context, deckAdapter);
        bgTask.execute(BackgroundTaskDetails.insertingDeck(OperationType.INSERT_DECK, new Deck(deckName)));
    }

    public void removeAllDecks(){
        BackgroundDeckTransaction bgTask = new BackgroundDeckTransaction(context, deckAdapter);
        bgTask.execute(BackgroundTaskDetails.removingDecks(OperationType.REMOVE_ALL_DECKS));
    }

    //Card operations

    public void fetchAllCardsInDeck(int deckId) {
        BackgroundCardTransaction bgTask = new BackgroundCardTransaction(context, cardAdapter);
        bgTask.delegate = this;
        bgTask.execute(BackgroundTaskDetails.fetchingCardsInDeck(OperationType.FETCH_ALL_CARDS_IN_DECK, deckId));
    }

    public void insertCard(Card inputCard) {
        BackgroundCardTransaction bgTask = new BackgroundCardTransaction(context, cardAdapter);
        bgTask.execute(BackgroundTaskDetails.insertingCard(OperationType.INSERT_CARD, inputCard));
    }

    @Override
    public void deliverDecks(ArrayList<Deck> freshDecks) {
        deckAdapter.addAll(freshDecks);
    }

    @Override
    public void deliverCards(ArrayList<Card> freshCards) {
        cardAdapter.addAll(freshCards);
    }
}
