package com.example.flashcards.data.background;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.flashcards.data.AppDatabase;
import com.example.flashcards.data.adapters.CardAdapter;
import com.example.flashcards.data.entities.Card;

import java.util.ArrayList;

public class BackgroundCardTransaction extends AsyncTask<BackgroundTaskDetails, Card, ArrayList<Card>> {
    private Context context;
    private AppDatabase database;
    private CardAdapter cardAdapter;
    public BackgroundTaskResponse delegate = null;

    public BackgroundCardTransaction(Context context,@NonNull CardAdapter cardAdapter) {
        this.context = context;
        this.database = AppDatabase.getDatabase(context);
        this.cardAdapter = cardAdapter;
    }

    @Override
    protected void onPostExecute(ArrayList<Card> freshCards) {
        if(freshCards != null && delegate != null)delegate.deliverCards(freshCards);
    }


    @Override
    protected ArrayList<Card> doInBackground(BackgroundTaskDetails... params) {
        BackgroundTaskDetails details = params[0];
        switch(details.getOperation()) {
            case INSERT_CARD:
                int toWhichDeck = details.getTargetId();
                Card inputCard = details.getInputCard();
                database.cardDao().insertAll(inputCard);
                break;

            case FETCH_ALL_CARDS_IN_DECK:
                int whatDeck = details.getTargetId();
                ArrayList<Card> freshCards = (ArrayList<Card>) database.cardDao().loadAllByDeckId(whatDeck);
                return freshCards;
        }

        return null;
    }
}
