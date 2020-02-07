package com.example.flashcards.data.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards.R;
import com.example.flashcards.data.DatabaseManager;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.views.CardListItem;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CardRVAdapter extends RecyclerView.Adapter<CardRVAdapter.ViewHolder>{

    private ArrayList<Card> cards;
    private Context context;
    private Card recentlyDeletedItem = null;
    private int recentlyDeletedItemPosition;
    private DatabaseManager dbManager;

    public CardRVAdapter(Context context, ArrayList<Card> cards) {
        this.cards = cards;
        this.context = context;
        dbManager = new DatabaseManager(context);
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        recentlyDeletedItem = cards.get(position);
        recentlyDeletedItemPosition = position;
        cards.remove(position);
        notifyItemRemoved(position);

        dbManager.markDeletedCard(recentlyDeletedItem);

        showUndoSnackBar();
    }

    private void showUndoSnackBar() {
        Activity activity = (Activity) context;
        View container = activity.findViewById(R.id.snack_bar_container);
        Snackbar snackbar = Snackbar.make(container, R.string.snackbar_text, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.snackbar_undo, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                undoDelete();
            }

        });
        snackbar.show();
    }

    private void undoDelete() {
        cards.add(recentlyDeletedItemPosition, recentlyDeletedItem);
        notifyItemInserted(recentlyDeletedItemPosition);

        dbManager.unmarkDeleted(recentlyDeletedItem);
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

        item.setCard(cards.get(position));
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
        if(cards == null) return 0;
        else {
            return cards.size();
        }
    }

    public Context getContext() {
        return context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
