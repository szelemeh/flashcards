package com.example.flashcards.data;

import com.example.flashcards.business.PracticeController;
import com.example.flashcards.data.background.BackgroundCardTransaction;
import com.example.flashcards.data.background.BackgroundTaskDetails;
import com.example.flashcards.data.background.BackgroundTaskResponse;
import com.example.flashcards.data.background.OperationType;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;

import java.util.ArrayList;
import java.util.Stack;

public class PracticeDataManager implements BackgroundTaskResponse {
    PracticeController controller;
    DatabaseManager dbManager;

    public PracticeDataManager(PracticeController controller) {
        this.controller = controller;
        this.dbManager = new DatabaseManager(controller.getContext());
    }

    public void loadCards() {
        BackgroundCardTransaction bgTask = new BackgroundCardTransaction(controller.getContext());
        bgTask.delegate = this;
        bgTask.execute(BackgroundTaskDetails.fetchingCardsInDeck(
                OperationType.FETCH_CARDS_FOR_PRACTICE, controller.getDeckId()));
    }


    @Override
    public void deliverCards(ArrayList<Card> cards) {
        Stack<Card> cardStack = new Stack<>();
        for(Card card: cards) {
            cardStack.push(card);
        }
        controller.setCardStack(cardStack);
    }

    @Override
    public void deliverDecks(ArrayList<Deck> decks) {}

    public void updateCard(Card cardLastNew) {
        dbManager.updateCard(cardLastNew);
    }
}
