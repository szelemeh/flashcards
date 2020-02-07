package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.flashcards.R;
import com.example.flashcards.SwipeToDeleteCallback;
import com.example.flashcards.data.DatabaseManager;
import com.example.flashcards.data.adapters.CardRVAdapter;
import com.example.flashcards.data.adapters.decorators.MarginItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DeckViewActivity extends AppCompatActivity {
    private int deckId;
    private String deckTitle;
    private CardRVAdapter cardAdapter;
    private Toolbar toolbar;
    private DatabaseManager dbManager;
    private FloatingActionButton fab;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_view_v1);

        initDeckInfo();

        initRecyclerView();

        initToolbar();

        initDbManager();

        initFab();
    }

    private void initFab() {
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CardAddActivity.class);
                intent.putExtra("deckId", deckId);
                intent.putExtra("deckTitle", deckTitle);
                startActivity(intent);
            }
        });
    }

    private void initDbManager() {
        dbManager = new DatabaseManager(this, cardAdapter);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.card_recycler_view);
        cardAdapter = new CardRVAdapter(this, null);
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new MarginItemDecoration(7));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(cardAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void initDeckInfo() {
        Bundle b = getIntent().getExtras();
        if (b == null) throw new NullPointerException("Bundle for this activity is null!");

        deckId = b.getInt("deckId");
        deckTitle = b.getString("deckTitle");
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setElevation(0);
        if(deckTitle == null) throw new NullPointerException();
        toolbar.setTitle(deckTitle);
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbManager.loadAllCardsInDeck(deckId);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
