package com.example.flashcards.views;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.flashcards.R;
import com.example.flashcards.activities.CardAddActivity;
import com.example.flashcards.activities.DeckViewActivity;

public class DeckListItem extends LinearLayout {
    private Context context;
    private TextView deckName;
    private TextView totalCardsNumber;
    private Button addCardBtn;
    private Button viewCardsBtn;
    private Button practiceBtn;
    private int deckId;

    public DeckListItem(final Context context, @Nullable AttributeSet attrs, final int deckId) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.deck_list_item, this, true);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DeckListItem);

        this.deckId = deckId;
        this.context = context;
        this.deckName = findViewById(R.id.deck_list_item_name);
        this.totalCardsNumber = findViewById(R.id.deck_list_item_cards_total);
        this.addCardBtn = findViewById(R.id.deck_list_item_add_card_btn);
        this.viewCardsBtn = findViewById(R.id.deck_list_item_view_all_btn);
        this.practiceBtn = findViewById(R.id.deck_list_item_practice_btn);

        this.deckName.setText(array.getString(R.styleable.DeckListItem_name));

        array.recycle();

        addCardBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CardAddActivity.class);
                Bundle b = new Bundle();
                b.putInt("deckId", deckId);
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });

        viewCardsBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DeckViewActivity.class);
                Bundle b = new Bundle();
                b.putInt("deckId", deckId);
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });
    }

    public void setDeckName(String newName) {
        this.deckName.setText(newName);
    }

    public void setTotalCardsNumber(Integer newNumber) {
        this.totalCardsNumber.setText(String.valueOf(newNumber));
    }
}
