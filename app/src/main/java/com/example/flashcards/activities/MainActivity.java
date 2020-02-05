package com.example.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.flashcards.MockData;
import com.example.flashcards.data.DatabaseManager;
import com.example.flashcards.R;
import com.example.flashcards.data.adapters.DeckRVAdapter;
import com.example.flashcards.data.adapters.decorators.MarginItemDecoration;
import com.example.flashcards.data.entities.Deck;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DeckRVAdapter deckAdapter;
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        initRecyclerView();

        initDbManager();

        initFloatActionButton();

        dbManager.loadAllDecks();
    }

    private void initDbManager() {
        dbManager = new DatabaseManager(this, deckAdapter);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.main_activity_title);
        setSupportActionBar(toolbar);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.deck_recycler_view);
        deckAdapter = new DeckRVAdapter(this, null);
        recyclerView.setAdapter(deckAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MarginItemDecoration(7));

    }

    private void initFloatActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbManager.loadAllDecks();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_add_new_deck:
                final EditText input = new EditText(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.new_deck)
                        .setView(input);

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        String deckName = input.getText().toString();
                        if(deckName.equals(""))return;

                        dbManager.addDeck(deckName);
                        onResume();
                    }
                });

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
        }
        return true;
    }
}
