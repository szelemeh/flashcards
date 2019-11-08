package com.example.flashcards.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.flashcards.data.daos.CardDao;
import com.example.flashcards.data.daos.DeckDao;
import com.example.flashcards.data.daos.SettingDao;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;
import com.example.flashcards.data.entities.Setting;

@Database(entities = {Setting.class, Deck.class, Card.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract SettingDao settingDao();
    public abstract DeckDao deckDao();
    public abstract CardDao cardDao();

}

// TODO: 01-Nov-19 Resolve issue with timezones(class Date does not support them)

