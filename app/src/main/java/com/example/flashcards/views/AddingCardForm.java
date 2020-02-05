package com.example.flashcards.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.flashcards.R;
import com.google.android.material.textfield.TextInputLayout;

public class AddingCardForm extends RelativeLayout {

    private Context context;
    private TextInputLayout questionInput;
    private TextInputLayout answerInput;



    public AddingCardForm(Context context) {
        super(context);
        this.context = context;

        initInflater();

        this.questionInput = findViewById(R.id.text_input_front);
        this.answerInput = findViewById(R.id.text_input_back);

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

}
