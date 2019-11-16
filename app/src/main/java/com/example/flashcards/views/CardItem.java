package com.example.flashcards.views;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.flashcards.R;
import com.example.flashcards.data.entities.Card;

import java.util.Date;

public class CardItem extends RelativeLayout {
    private TextView content;
    private EditText edit;
    private Button flipBtn;
    private boolean isFront;
    private Card card;
    private Context context;
    
    public CardItem(Context context, final Card card) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.card_item, this, true);

        this.context = context;
        this.card = card;
        content = findViewById(R.id.card_item_content);
        edit = findViewById(R.id.card_item_edit);
        edit.setVisibility(View.GONE);
        flipBtn = findViewById(R.id.card_item_flip_side);
        setContent(card.frontSide);
        isFront = true;

        flipBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                if(isFront){
                    isFront = false;
                    setContent(card.backSide);
                }
                else {
                    isFront = true;
                    setContent(card.frontSide);
                }
            }
        });
    }

    public CardItem(Context context, int deckId) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.card_item, this, true);

        this.context = context;
        card = new Card("","", deckId, new Date());
        content = findViewById(R.id.card_item_content);
        if(content == null) Log.d("null", "fuck");
        content.setVisibility(View.GONE);
        edit = findViewById(R.id.card_item_edit);
        flipBtn = findViewById(R.id.card_item_flip_side);

        isFront = true;
        flipBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                if(isFront){
                    isFront = false;
                    card.frontSide = edit.getText().toString();
                    edit.setText(card.backSide);
                }
                else {
                    isFront = true;
                    card.backSide = edit.getText().toString();
                    edit.setText(card.frontSide);
                }
            }
        });
    }

    private void setContent(String other) {
        content.setText(other);
    }

    public Card getCard() {
        return card;
    }

    public void refresh() {
        if(isFront){
            card.frontSide = edit.getText().toString();
        }
        else {
            card.backSide = edit.getText().toString();
        }
    }
}
