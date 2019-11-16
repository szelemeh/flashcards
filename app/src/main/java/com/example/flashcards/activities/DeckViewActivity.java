package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.flashcards.R;
import com.example.flashcards.data.adapters.CardAdapter;
import com.example.flashcards.data.DatabaseOperation;

public class DeckViewActivity extends AppCompatActivity {
    private int deckId;
    private DatabaseOperation databaseOperation;
    private CardAdapter cardAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle b = getIntent().getExtras();
        deckId = b != null ? b.getInt("deckId") : -1;

        this.cardAdapter = new CardAdapter(this, R.id.card_container_deck_view);
        this.databaseOperation = new DatabaseOperation(this, cardAdapter);


        databaseOperation.fetchAllCardsInDeck(deckId);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
