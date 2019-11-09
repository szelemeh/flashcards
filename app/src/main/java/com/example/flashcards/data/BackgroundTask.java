package com.example.flashcards.data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.flashcards.DatabaseServices;
import com.example.flashcards.DeckAdapter;
import com.example.flashcards.data.entities.Deck;

import java.util.ArrayList;
import java.util.List;

public class BackgroundTask extends AsyncTask<String, Deck, String> {
    private Context context;
    private AppDatabase database;
    private DatabaseServices dbService;
    private DeckAdapter deckAdapter;

    public BackgroundTask(Context context, DatabaseServices dbService, DeckAdapter deckAdapter) {
        this.context = context;
        this.database = AppDatabase.getDatabase(context);
        this.dbService = dbService;
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
                publishProgress(decks.toArray(new Deck[decks.size()]));
                break;
            case "nuke_decks":
                database.deckDao().nukeDecks();
                deckAdapter.clear();
        }

        return null;
    }
}
