package com.example.flashcards.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.flashcards.R;
import com.example.flashcards.activities.CardAddActivity;
import com.example.flashcards.activities.DeckViewActivity;
import com.example.flashcards.data.entities.Deck;

public class DeckItem extends LinearLayout {
    private Context context;
    private TextView deckName;
    private TextView cardsNumber;
    private Button addCardBtn;
    private Button viewCardsBtn;
    private Button practiceBtn;
    private final Deck deck;


    public DeckItem(final Context context, final Deck deck) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.deck_item, this, true);

        this.deck = deck;
        this.deckName = findViewById(R.id.deck_list_item_name);
        this.cardsNumber = findViewById(R.id.deck_list_item_cards_total);
        this.context = context;
        this.addCardBtn = findViewById(R.id.deck_list_item_add_card_btn);
        this.viewCardsBtn = findViewById(R.id.deck_list_item_view_all_btn);
        this.practiceBtn = findViewById(R.id.deck_list_item_practice_btn);

        setDeckName(deck.deckName);
        setCardsNumber(deck.totalCardsNumber);

        addCardBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CardAddActivity.class);
                Bundle b = new Bundle();
                b.putInt("deckId", deck.getId());
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });

        viewCardsBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DeckViewActivity.class);
                Bundle b = new Bundle();
                b.putInt("deckId", deck.getId());
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });
    }

    public void setDeckName(String newName) {
        this.deckName.setText(newName);
    }

    public void setCardsNumber(Integer newNumber) {
        this.cardsNumber.setText(String.valueOf(newNumber));
    }
}
