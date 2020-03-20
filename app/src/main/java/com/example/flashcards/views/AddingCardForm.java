package com.example.flashcards.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
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

    private Card card = null;
    DatabaseManager dbManager;
    private int deckId;

    private MenuItem savingToogleView;


    public AddingCardForm(Context context, int deckId) {
        super(context);
        this.context = context;
        this.deckId = deckId;
        dbManager = new DatabaseManager(context);


        initInflater();

        this.questionInput = findViewById(R.id.text_input_front);
        this.answerInput = findViewById(R.id.text_input_back);

        initEditTextsListeners();

        prepareForTheNextEntry();
    }

    private void initEditTextsListeners() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(savingToogleView != null) savingToogleView.setVisible(true);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
        questionInput.getEditText().addTextChangedListener(textWatcher);
        answerInput.getEditText().addTextChangedListener(textWatcher);
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

    public void setContent(String question, String answer) {
        questionInput.getEditText().setText(question);
        answerInput.getEditText().setText(answer);
    }

    public void setSavingToggleView(MenuItem item) {
        savingToogleView = item;
    }
}
