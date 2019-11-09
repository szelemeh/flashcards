package com.example.flashcards;

import android.content.Context;
import com.example.flashcards.data.AppDatabase;
import com.example.flashcards.data.BackgroundTask;

public class DatabaseServices {
    private final Context context;
    private final AppDatabase database;
    private DeckAdapter deckAdapter;


    public DatabaseServices(Context context, DeckAdapter deckAdapter) {
        this.context = context;
        this.database = AppDatabase.getDatabase(context);
        this.deckAdapter = deckAdapter;
    }

    public void loadAllDecks() {
        BackgroundTask bgTask = new BackgroundTask(context, this, deckAdapter);
        bgTask.execute("load_all_decks");
    }


    public void addDeck(String name) {
        BackgroundTask bgTask = new BackgroundTask(context, this, deckAdapter);
        bgTask.execute("add_deck", name);
    }

    public void removeAllDecks(){
        BackgroundTask bgTask = new BackgroundTask(context, this, deckAdapter);
        bgTask.execute("nuke_decks");
    }

}
