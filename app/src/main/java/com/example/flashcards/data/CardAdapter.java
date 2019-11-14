package com.example.flashcards.data;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.views.CardItem;
import java.util.ArrayList;
import java.util.Collection;

public class CardAdapter {
    private Context context;
    private ViewGroup container;
    private ArrayList<Card> listOfCards;
    private ArrayList<Integer> listOfIds;

    public CardAdapter(Context context, int containerId) {
        this.context = context;
        this.container = ((Activity)context).findViewById(containerId);
        this.listOfCards = new ArrayList<>();
        this.listOfIds = new ArrayList<>();
    }

    public void addAll(ArrayList<Card> freshCards) {
        for (Card freshCard : freshCards) {
            if (freshCard != null && !listOfIds.contains(freshCard.getId())) {
                listOfIds.add(freshCard.getId());
                listOfCards.add(freshCard);
                container.addView(getView(freshCard));
            }
        }
    }

    public View getView(Card card) {
        CardItem item = new CardItem(context, card);
        return item;
    }
}
