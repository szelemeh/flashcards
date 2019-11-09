package com.example.flashcards;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.flashcards.data.entities.Deck;
import com.example.flashcards.views.DeckListItem;

import java.util.ArrayList;

public class DeckAdapter {
    private Context context;
    private Activity activity;
    private ViewGroup container;
    private ArrayList<String> list;

    public DeckAdapter(Context context, int listId) {
        this.context = context;
        this.activity = (Activity)context;
        this.container = activity.findViewById(listId);
        this.list = new ArrayList<String>();
    }

    public void addItems(Deck... decks){

        for(Deck deck : decks) {
            if(!list.contains(deck.deckName)) {
                DeckListItem item = new DeckListItem(context, null);
                item.setDeckName(deck.deckName);
                list.add(deck.deckName);
                container.addView(item);
            }
        }
    }

    public void clear() {
        list.clear();
        container.removeAllViewsInLayout();
    }


}
