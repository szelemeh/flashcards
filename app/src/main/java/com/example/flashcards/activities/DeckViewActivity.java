package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.flashcards.MockData;
import com.example.flashcards.R;
import com.example.flashcards.data.adapters.CardAdapter;
import com.example.flashcards.data.DatabaseExecutor;
import com.example.flashcards.data.adapters.CardRVAdapter;
import com.example.flashcards.data.adapters.decorators.MarginItemDecoration;

public class DeckViewActivity extends AppCompatActivity {
    private int deckId;
    private DatabaseExecutor databaseExecutor;
    private CardRVAdapter cardAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_view);

        initDeckId();

        initToolbar();

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.card_recycler_view);
        cardAdapter = new CardRVAdapter(this, MockData.getCardsFromDeck(this.deckId));
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new MarginItemDecoration(7));

    }

    private void initDeckId() {
        Bundle b = getIntent().getExtras();
        deckId = b != null ? b.getInt("deckId") : -1;
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
