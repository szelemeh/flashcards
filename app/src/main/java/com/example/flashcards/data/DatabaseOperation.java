package com.example.flashcards.data;

import android.content.Context;

public class DatabaseOperation {
    private final Context context;
    private final AppDatabase database;
    private DeckAdapter deckAdapter;

    public DatabaseOperation(Context context, DeckAdapter deckAdapter) {
        this.context = context;
        this.database = AppDatabase.getDatabase(context);
        this.deckAdapter = deckAdapter;
    }

    public DatabaseOperation(Context context) {
        this.context = context;
        this.database = AppDatabase.getDatabase(context);
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

    public void addCard(String frontText, String backText, String deckId) {
        BackgroundTask bgTask = new BackgroundTask(context, this, deckAdapter);
        bgTask.execute("add_card", frontText, backText, deckId);
    }
}
