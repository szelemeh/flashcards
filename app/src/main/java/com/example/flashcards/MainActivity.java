package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;
import android.os.Bundle;

import com.example.flashcards.data.AppDatabase;

public class MainActivity extends AppCompatActivity {
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "FlashCards.db").build();
        // TODO: 03-Nov-19 add button for creating a new deck on the header panel,
        //      which will open dialog where user would be able to create new deck

        // TODO: 03-Nov-19 Make MainActivity scrollable
        // TODO: 03-Nov-19 load decks from the database and put them in DeckListItems
    }
}
