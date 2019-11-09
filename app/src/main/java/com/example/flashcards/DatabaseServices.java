package com.example.flashcards;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;

import com.example.flashcards.data.AppDatabase;
import com.example.flashcards.data.entities.Deck;

import java.util.ArrayList;
import java.util.List;

public class DatabaseServices {
    private Context context;
    private AppDatabase database;

    public DatabaseServices(Context context) {
        this.context = context;

        database = Room.databaseBuilder(this.context,
                AppDatabase.class, "FlashCards.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    public ArrayList<Deck> getAllDecks() {
        return (ArrayList<Deck>) new GetDecksAsyncTask().execute().get();
    }
    private class GetDecksAsyncTask extends AsyncTask<Void, Void, List<Deck>>
    {
        @Override
        protected List<Deck> doInBackground(Void... url) {
            return database.deckDao().getAll();
        }
    }

    public void addDeck(Deck deck) {
        new AddDeckAsyncTask().execute(deck);
    }

    private class AddDeckAsyncTask extends AsyncTask<Deck, Void, Void>
    {
        @Override
        protected Void doInBackground(Deck... decks) {
            database.deckDao().insertAll(decks);
            return null;
        }
    }
}
