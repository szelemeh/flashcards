package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.flashcards.R;
import com.example.flashcards.data.adapters.DeckRVAdapter;
import com.example.flashcards.data.adapters.decorators.MarginItemDecoration;
import com.example.flashcards.data.entities.Deck;

import java.util.ArrayList;

public class DeckPracticeActivity extends AppCompatActivity {

    private ArrayList<Deck> decks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_practice);

        initDecks();

        initRecyclerView();
    }

    private void initDecks() {
        decks.add(new Deck("English"));
        decks.add(new Deck("Math"));
        decks.add(new Deck("History"));
        decks.add(new Deck("Biology"));
        decks.add(new Deck("Chemistry"));
        decks.add(new Deck("Law"));
        decks.add(new Deck("Geography"));
        decks.add(new Deck("Literature"));
        decks.add(new Deck("Self-improvement"));
        decks.add(new Deck("Spanish"));
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.deck_recycler_view);
        DeckRVAdapter adapter = new DeckRVAdapter(this, decks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MarginItemDecoration(10));

    }
}
