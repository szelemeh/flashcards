package com.example.flashcards.data;

import android.content.Context;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.flashcards.data.adapters.CardRVAdapter;
import com.example.flashcards.data.adapters.DeckRVAdapter;
import com.example.flashcards.data.background.BackgroundCardTransaction;
import com.example.flashcards.data.background.BackgroundDeckTransaction;
import com.example.flashcards.data.background.BackgroundTaskDetails;
import com.example.flashcards.data.background.BackgroundTaskResponse;
import com.example.flashcards.data.background.OperationType;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;

import java.util.ArrayList;

public class DatabaseManager implements DatabaseManagerInterface, BackgroundTaskResponse {

    Context context;
    DeckRVAdapter deckRVAdapter = null;
    CardRVAdapter cardRVAdapter = null;

    SwipeRefreshLayout swipeRefresh = null;

    public DatabaseManager(Context context, DeckRVAdapter deckRVAdapter) {
        this.context = context;
        this.deckRVAdapter = deckRVAdapter;
    }

    public DatabaseManager(Context context, CardRVAdapter cardRVAdapter) {
        this.context = context;
        this.cardRVAdapter = cardRVAdapter;
    }

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public SwipeRefreshLayout getSwipeRefresh() {
        return swipeRefresh;
    }

    public void setSwipeRefresh(SwipeRefreshLayout swipeRefresh) {
        this.swipeRefresh = swipeRefresh;
    }

    @Override
    public void addDeck(String deckName) {
        BackgroundDeckTransaction bgTask = new BackgroundDeckTransaction(context, deckRVAdapter);
        bgTask.execute(BackgroundTaskDetails.insertingDeck(OperationType.INSERT_DECK, new Deck(deckName)));
    }

    @Override
    public void updateDeck(Deck updatedDeck) {

    }

    public void deleteDeck(int deckId) {
        BackgroundDeckTransaction bgTask = new BackgroundDeckTransaction(context, deckRVAdapter);
        bgTask.execute(BackgroundTaskDetails.removingDecks(OperationType.REMOVE_DECK, deckId));
    }

    @Override
    public Deck getDeck(int deckId) {
        return null;
    }

    @Override
    public void loadAllDecks() {
        BackgroundDeckTransaction bgTask = new BackgroundDeckTransaction(context, deckRVAdapter);
        bgTask.delegate = this;
        bgTask.execute(BackgroundTaskDetails.fetchingDecks(OperationType.FETCH_ALL_DECKS));
    }

    @Override
    public void addCard(Card newCard) {
        BackgroundCardTransaction bgTask = new BackgroundCardTransaction(context);
        bgTask.execute(BackgroundTaskDetails.insertingCard(OperationType.INSERT_CARD, newCard));
    }

    @Override
    public void updateCardContent(Card changedCard) {
        BackgroundCardTransaction bgTask = new BackgroundCardTransaction(context);
        bgTask.execute(BackgroundTaskDetails.updatingCard(OperationType.UPDATE_CARD_CONTENT, changedCard));
    }


    public void markDeletedCard(Card card) {
        BackgroundCardTransaction bgTask = new BackgroundCardTransaction(context);
        bgTask.execute(BackgroundTaskDetails.updatingCard(OperationType.MARK_DELETED, card));
    }

    public void unmarkDeleted(Card card) {
        BackgroundCardTransaction bgTask = new BackgroundCardTransaction(context);
        bgTask.execute(BackgroundTaskDetails.updatingCard(OperationType.UNMARK_DELETED, card));
    }

    @Override
    public void deleteCard(Card card) {
        BackgroundCardTransaction bgTask = new BackgroundCardTransaction(context);
        bgTask.execute(BackgroundTaskDetails.removingCard(OperationType.REMOVE_CARD, card));
    }

    @Override
    public void getCard(int cardId) {

    }


    @Override
    public void loadAllCards() {

    }

    @Override
    public void loadAllCardsInDeck(int deckId) {
        BackgroundCardTransaction bgTask = new BackgroundCardTransaction(context);
        bgTask.delegate = this;
        bgTask.execute(BackgroundTaskDetails.fetchingCardsInDeck(OperationType.FETCH_ALL_CARDS_IN_DECK, deckId));
    }


    public void renameDeck(int deckId, String newDeckName) {
        BackgroundDeckTransaction bgTask = new BackgroundDeckTransaction(context, deckRVAdapter);
        bgTask.execute(BackgroundTaskDetails.updatingDeck(OperationType.RENAME_DECK, deckId, newDeckName));
    }

    @Override
    public void deliverDecks(ArrayList<Deck> decks) {
        if(deckRVAdapter == null) throw new NullPointerException("deckRVAdapter is null!");
        deckRVAdapter.setDecks(decks);

        if(swipeRefresh != null)swipeRefresh.setRefreshing(false);
    }

    @Override
    public void deliverCards(ArrayList<Card> cards) {
        if(cardRVAdapter == null) throw new NullPointerException("cardRVAdapter is null!");
        cardRVAdapter.setCards(cards);

        if(swipeRefresh != null)swipeRefresh.setRefreshing(false);
    }
}
