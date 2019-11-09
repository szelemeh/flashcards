package com.example.flashcards.data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;

import java.util.ArrayList;
import java.util.Date;

public class BackgroundTask extends AsyncTask<String, Deck, String> {
    private Context context;
    private AppDatabase database;
    private DatabaseOperation dbOperation;
    private DeckAdapter deckAdapter;
    public static final String DATE_FORMAT = "yyyy/mm/dd hh:mm";

    public BackgroundTask(Context context, DatabaseOperation dbOperation, DeckAdapter deckAdapter) {
        this.context = context;
        this.database = AppDatabase.getDatabase(context);
        this.dbOperation = dbOperation;
        this.deckAdapter = deckAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Deck... values) {
        deckAdapter.addItems(values);
    }

    @Override
    protected void onPostExecute(String message) {
        super.onPostExecute(message);

    }

    @Override
    protected String doInBackground(String... params) {
        String operation = params[0];
        switch(operation) {
            case "add_deck":
                Deck newDeck = new Deck(params[1]);
                database.deckDao().insertAll(newDeck);
                break;
            case "load_all_decks":
                ArrayList<Deck> decks = (ArrayList<Deck>)database.deckDao().getAll();
                for(Deck deck: decks) {
                    deck.totalCardsNumber = database.deckDao().getTotalCardsNumberInDeck(deck.getId());
                }
                publishProgress(decks.toArray(new Deck[decks.size()]));
                break;
            case "nuke_decks":
                database.deckDao().nukeDecks();
                database.cardDao().nukeCards();
                deckAdapter.clear();
                break;
            case "add_card":
                int deckId = Integer.parseInt(params[3]);
                Card newCard = new Card(params[1], params[2], deckId, new Date());
                database.cardDao().insertAll(newCard);
                break;
        }

        return null;
    }
}
