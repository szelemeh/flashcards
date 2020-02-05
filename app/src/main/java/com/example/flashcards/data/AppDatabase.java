package com.example.flashcards.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.flashcards.data.daos.CardDao;
import com.example.flashcards.data.daos.DeckDao;
import com.example.flashcards.data.daos.SettingDao;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;
import com.example.flashcards.data.entities.Setting;

@Database(entities = {Setting.class, Deck.class, Card.class}, version = 11, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static final String DB_NAME = "FlashCards.db";

    public abstract SettingDao settingDao();
    public abstract DeckDao deckDao();
    public abstract CardDao cardDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            AppDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

// TODO: 01-Nov-19 Resolve issue with timezones(class Date does not support them)

