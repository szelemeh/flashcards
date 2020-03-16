package com.example.flashcards.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import androidx.room.Database;

import com.example.flashcards.R;
import com.example.flashcards.data.DatabaseManager;
import com.example.flashcards.data.entities.Card;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

public class AddingCardForm extends RelativeLayout {

    private Context context;
    private TextInputLayout questionInput;
    private TextInputLayout answerInput;

    private boolean cardAddedToDatabase = false; //whether a card was already created in this form and added to database
    private Card card = null;
    DatabaseManager dbManager;
    private int deckId;




    public AddingCardForm(Context context, int deckId) {
        super(context);
        this.context = context;
        this.deckId = deckId;
        dbManager = new DatabaseManager(context);


        initInflater();

        this.questionInput = findViewById(R.id.text_input_front);
        this.answerInput = findViewById(R.id.text_input_back);

        prepareForTheNextEntry();
    }

    private void initInflater() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        inflater.inflate(R.layout.adding_card_form, this);
    }

    public String getQuestion() {
        return questionInput.getEditText().getText().toString().trim();
    }
    public String getAnswer() {
        return answerInput.getEditText().getText().toString().trim();
    }

    public boolean isEmpty() {
        return isStringBlank(getQuestion()) && isStringBlank(getAnswer());
    }

    private boolean isStringBlank(String s) {
        if(s == null) return true;
        for(int i=0; i<s.length(); i++) {
            if(!Character.isWhitespace(s.charAt(i))) return false;
        }
        return true;
    }

    public void clear() {
        questionInput.getEditText().setText("");
        answerInput.getEditText().setText("");

        prepareForTheNextEntry();
    }

    private void prepareForTheNextEntry() {
        questionInput.getEditText().requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(questionInput.getEditText(), InputMethodManager.SHOW_IMPLICIT);

    }

    public void save() {
        if (cardAddedToDatabase) {
            Card card = getCard();
            dbManager.updateCardContent(card);
        } else {
            dbManager.addCard(getCard());
            cardAddedToDatabase = true;
        }
    }

    private Card getCard() {
        if (card != null){
            card.setFront(getQuestion());
            card.setBack(getAnswer());
            return card;
        }
        else {
            card = new Card(getQuestion(), getAnswer(), deckId, new Date());
            return card;
        }
    }

}
