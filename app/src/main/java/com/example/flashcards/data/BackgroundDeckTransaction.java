package com.example.flashcards.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;
import java.util.ArrayList;
import java.util.List;

public class BackgroundDeckTransaction extends AsyncTask<BackgroundTaskDetails, Deck, ArrayList<Deck>> {
    private Context context;
    private AppDatabase database;
    private DeckAdapter adapter;
    public BackgroundTaskResponse delegate = null;

    public BackgroundDeckTransaction(Context context,@NonNull DeckAdapter adapter) {
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
                for(Deck deck: decks) {
                    deck.totalCardsNumber = database.deckDao().getTotalCardsNumberInDeck(deck.getId());
                }
                return decks;

            case REMOVE_ALL_DECKS:
                database.deckDao().nukeDecks();
                database.cardDao().nukeCards();
                adapter.clear();
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
