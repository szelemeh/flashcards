package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.flashcards.R;
import com.example.flashcards.data.CardAdapter;
import com.example.flashcards.data.DatabaseOperation;

public class DeckViewActivity extends AppCompatActivity {
    private int deckId;
    DatabaseOperation databaseOperation;
    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_view);

        Bundle b = getIntent().getExtras();
        deckId = b != null ? b.getInt("deckId") : -1;

        this.cardAdapter = new CardAdapter(this, R.id.card_container_deck_view);
        this.databaseOperation = new DatabaseOperation(this, cardAdapter);


        databaseOperation.fetchAllCardsInDeck(deckId);
    }
}
