package com.example.flashcards.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.flashcards.R;
import com.example.flashcards.data.entities.Deck;

public class DeckListItem extends RelativeLayout {

    private Context context;


    private TextView deckName;
    private RelativeLayout parentLayout;

    private Deck deck = null;

    public DeckListItem(Context context) {
        super(context);

        this.context = context;

        initInflater();

        this.deckName = findViewById(R.id.deck_list_item_name);
        this.parentLayout = findViewById(R.id.deck_list_item_container);

    }

    private void initInflater() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert inflater != null;
        inflater.inflate(R.layout.deck_list_item, this, true);

    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }

    public RelativeLayout getParentLayout() {
        return parentLayout;
    }

    public void updateDeckName() {
        if(deck != null) deckName.setText(deck.deckName);
    }

    public void refresh() {
        updateDeckName();
    }
}
