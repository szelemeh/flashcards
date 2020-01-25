package com.example.flashcards.data.background;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.flashcards.data.AppDatabase;
import com.example.flashcards.data.adapters.DeckRVAdapter;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;
import java.util.ArrayList;

public class BackgroundDeckTransaction extends AsyncTask<BackgroundTaskDetails, Deck, ArrayList<Deck>> {
    private Context context;
    private AppDatabase database;
    private DeckRVAdapter adapter;
    public BackgroundTaskResponse delegate = null;

    public BackgroundDeckTransaction(Context context, @NonNull DeckRVAdapter adapter) {
        this.context = context;
        this.database = AppDatabase.getDatabase(context);
        this.adapter = adapter;
    }

    @Override
    protected void onPostExecute(ArrayList<Deck> freshDecks) {
        if(freshDecks != null && delegate != null)delegate.deliverDecks(freshDecks);
    }


    @Override
    protected ArrayList<Deck> doInBackground(BackgroundTaskDetails... params) {
        BackgroundTaskDetails details = params[0];
        switch(details.getOperation()) {
            case INSERT_DECK:
                Deck inputDeck = details.getInputDeck();
                database.deckDao().insertAll(inputDeck);
                break;

            case FETCH_ALL_DECKS:
                ArrayList<Deck> decks = (ArrayList<Deck>) database.deckDao().getAll();
                return decks;

            case REMOVE_ALL_DECKS:
                database.cardDao().nukeCards();
                database.deckDao().nukeDecks();
                adapter.setDecks(new ArrayList<Deck>());
                break;

            case INSERT_CARD:
                int toWhichDeck = details.getTargetId();
                Card inputCard = details.getInputCard();
                database.cardDao().insertAll(inputCard);
                break;
        }
        return null;
    }
}
