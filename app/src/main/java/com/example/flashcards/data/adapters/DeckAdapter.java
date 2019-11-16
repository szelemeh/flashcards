package com.example.flashcards.data.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.flashcards.data.entities.Deck;
import com.example.flashcards.views.DeckItem;

import java.util.ArrayList;

public class DeckAdapter {
    private Context context;
    private ArrayList<Deck> listOfItems;
    private ArrayList<Integer> listOfIds;
    private ViewGroup container;

    public DeckAdapter(@NonNull Context context, int containerId) {
        this.context = context;
        this.listOfItems = new ArrayList<>();
        this.listOfIds = new ArrayList<>();
        this.container = ((Activity)context).findViewById(containerId);

    }

    public void addAll(ArrayList<Deck> freshDecks) {
        container.removeAllViewsInLayout();
        listOfItems.clear();
        for(Deck freshDeck : freshDecks) {
            listOfItems.add(freshDeck);
            container.addView(getView(freshDeck));
        }
    }

    private DeckItem getView(Deck freshDeck) {
        DeckItem freshItem = new DeckItem(context, freshDeck);
        return freshItem;
    }

    public void clear() {
        container.removeAllViewsInLayout();
        listOfItems.clear();
    }

}
