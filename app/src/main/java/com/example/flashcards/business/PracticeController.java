package com.example.flashcards.business;

import android.content.Context;
import com.example.flashcards.data.PracticeDataManager;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.ui.practice.ReplyQuality;

import java.util.Date;
import java.util.Stack;

public class PracticeController {

    private PracticeDataManager dm;

    private int deckId;
    private Context context;
    private Stack<Card> cardStack;
    private boolean sizeCheck = false;

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

    public Card reply(ReplyQuality q) {
        saveLast();

        cardLastOld = cardCurrent;

        if(cardCurrent == null)
            return null;

        cardLastNew = SpacedRepetitionHelper.calculateNextRepetitionDate(
                cardCurrent, q, new Date());

        nextCard();

        return cardCurrent;
    }

    private void saveLast() {
        if(cardLastNew != null)dm.updateCard(cardLastNew);
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
}
