package com.example.flashcards.data;

import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;

import java.util.ArrayList;

public class BackgroundTaskDetails {
    private OperationType operation; //for all cases
    private Deck inputDeck; //when adding a deck to the database
    private Card inputCard; //when adding a card to the database

    private int targetId; //when fetching a card or a id from the database

    private BackgroundTaskDetails(OperationType operation,
                                  Deck deck) {
        this.operation = operation;
        this.inputDeck = deck;
    }

    private BackgroundTaskDetails(OperationType operation,
                                  Card card) {
        this.operation = operation;
        this.inputCard = card;
    }

    private BackgroundTaskDetails(OperationType operation,
                                  int targetId) {
        this.operation = operation;
        this.targetId = targetId;
    }

    private BackgroundTaskDetails(OperationType operation) {
        this.operation = operation;
    }



    public static BackgroundTaskDetails insertingCard(OperationType operation,
                                                   Card card) {
        return new BackgroundTaskDetails(operation, card);
    }

    public static BackgroundTaskDetails insertingDeck(OperationType operation,
                                                   Deck deck) {
        return new BackgroundTaskDetails(operation, deck);
    }

    public static BackgroundTaskDetails fetchingSingleItem(OperationType operation,
                                                   int targetId) {
        return new BackgroundTaskDetails(operation, targetId);
    }

    public static BackgroundTaskDetails fetchingCardsInDeck(OperationType operation, int deckId) {
        return new BackgroundTaskDetails(operation, deckId);
    }

    public static BackgroundTaskDetails fetchingDecks(OperationType operation) {
        return new BackgroundTaskDetails(operation);
    }

    public static BackgroundTaskDetails removingDecks(OperationType operation) {
        return new BackgroundTaskDetails(operation);
    }

    public static BackgroundTaskDetails removingCards(OperationType operation) {
        return new BackgroundTaskDetails(operation);
    }



    public OperationType getOperation() {
        return operation;
    }

    public Deck getInputDeck() {
        return inputDeck;
    }

    public void setInputDeck(Deck inputDeck) {
        this.inputDeck = inputDeck;
    }

    public Card getInputCard() {
        return inputCard;
    }

    public void setInputCard(Card inputCard) {
        this.inputCard = inputCard;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }
}
