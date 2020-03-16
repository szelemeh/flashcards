package com.example.flashcards.data.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards.AlertUser;
import com.example.flashcards.activities.DeckViewActivity;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;
import com.example.flashcards.views.DeckListItem;

import java.util.ArrayList;

public class DeckRVAdapter extends RecyclerView.Adapter<DeckRVAdapter.ViewHolder>{

    private ArrayList<Deck> decks;
    private Context context;

    public DeckRVAdapter(Context context, ArrayList<Deck> decks) {
        this.context = context;
        this.decks = decks;
    }

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = new DeckListItem(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final DeckListItem item = (DeckListItem) holder.itemView;

        item.setDeck(decks.get(position));
        item.refresh();

        RelativeLayout container = item.getParentLayout();



        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DeckViewActivity.class);
                intent.putExtra("deckId", item.getDeck().getId());
                intent.putExtra("deckTitle", item.getDeck().deckName);
                context.startActivity(intent);
            }
        });

        container.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                AlertUser.makeToast(context, "Long clicked on " + item.getDeck().deckName);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(decks == null) return 0;
        else {
            return decks.size();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
