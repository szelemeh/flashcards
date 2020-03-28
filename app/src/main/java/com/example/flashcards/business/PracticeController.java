package com.example.flashcards.business;

import android.content.Context;
import android.view.View;

import com.example.flashcards.data.PracticeDataManager;
import com.example.flashcards.data.entities.Card;

import java.util.Date;
import java.util.Stack;

public class PracticeController {
    private static final int LOW_QUALITY = 1;
    private static final int MEDIUM_QUALITY = 3;
    private static final int HIGH_QUALITY = 5;

    private PracticeDataManager dm;

    private int deckId;
    private Context context;
    private Stack<Card> cardStack;
    private boolean sizeCheck = true;

    private Card cardCurrent;
    private Card cardLastOld;
    private Card cardLastNew;



    public PracticeController(Context context, int deckId) {
        this.context = context;
        this.deckId = deckId;

        dm = new PracticeDataManager(this);
        dm.loadCards();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(cardStack.empty())cardCurrent = null;
                else cardCurrent = cardStack.pop();
            }
        },30);
    }

    public Context getContext() {
        return context;
    }

    public int getDeckId() {
        return deckId;
    }

    public Card getCardCurrent() {
        return cardCurrent;
    }

    public void setCardStack(Stack<Card> cardStack) {
        this.cardStack = cardStack;
    }

    public Card undo() {
        cardCurrent = cardLastOld;
        cardLastNew = null;
        cardLastOld = null;
        return cardCurrent;
    }

    private void nextCard() {
        if(sizeCheck) {
            if(cardStack.size() < 3){
                dm.loadCards();
            }
        }

        if(cardStack.empty()) cardCurrent = null;
        else cardCurrent = cardStack.pop();
    }

    public void finishPractice() {
        saveLast();
    }

    public Card lowQualityReply() {
        saveLast();

        cardLastOld = cardCurrent;
        cardLastNew = SpacedRepetitionHelper.calculateNextRepetitionDate(
                cardCurrent, LOW_QUALITY, new Date());

        nextCard();

        return cardCurrent;
    }

    private void saveLast() {
        if(cardLastNew != null)dm.updateCard(cardLastNew);
    }

    public Card mediumQualityReply() {
        saveLast();

        cardLastOld = cardCurrent;
        cardLastNew = SpacedRepetitionHelper.calculateNextRepetitionDate(
                cardCurrent, MEDIUM_QUALITY, new Date());

        nextCard();

        return cardCurrent;
    }

    public Card highQualityReply() {
        saveLast();

        cardLastOld = cardCurrent;
        cardLastNew = SpacedRepetitionHelper.calculateNextRepetitionDate(
                cardCurrent, HIGH_QUALITY, new Date());

        nextCard();

        return cardCurrent;
    }
}
