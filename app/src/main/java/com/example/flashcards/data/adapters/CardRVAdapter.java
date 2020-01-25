package com.example.flashcards.data.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards.data.entities.Card;
import com.example.flashcards.views.CardListItem;

import java.util.ArrayList;

public class CardRVAdapter extends RecyclerView.Adapter<CardRVAdapter.ViewHolder>{

    private ArrayList<Card> cards;
    private Context context;

    public CardRVAdapter(Context context, ArrayList<Card> cards) {
        this.cards = cards;
        this.context = context;
    }

    public void setDecks(ArrayList<Card> decks) {
        this.cards = decks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = new CardListItem(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CardListItem item = (CardListItem) holder.itemView;

        item.setDeck(cards.get(position));
        item.refresh();

        RelativeLayout container = item.getParentLayout();

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.flip();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
