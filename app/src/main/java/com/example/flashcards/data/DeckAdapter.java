package com.example.flashcards.data;

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
    private ArrayList<Integer> list;

    public DeckAdapter(Context context, int listId) {
        this.context = context;
        this.activity = (Activity)context;
        this.container = activity.findViewById(listId);
        this.list = new ArrayList<Integer>();
    }

    public void addItems(Deck... decks){

        for(Deck deck : decks) {
            if(!list.contains(deck.getId()))  { //better change to ids
                DeckListItem item = new DeckListItem(context, null, deck.getId());
                item.setDeckName(deck.deckName);
                item.setTotalCardsNumber(deck.totalCardsNumber);
                list.add(deck.getId());
                container.addView(item);
            }
        }
    }

    public void clear() {
        list.clear();
        container.removeAllViewsInLayout();
    }


}
