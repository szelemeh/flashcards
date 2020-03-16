package com.example.flashcards.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
    private SwipeRefreshLayout swipeRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_view);

        initDeckInfo();

        initSwipeRefresh();

        initRecyclerView();

        initToolbar();

        initDbManager();

        initFab();
    }

    private void initSwipeRefresh() {
        swipeRefresh = findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        refresh();
                    }
                }
        );
    }

    private void refresh() {
        toolbar.setTitle(deckTitle);
        dbManager.loadAllCardsInDeck(deckId);
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
        dbManager.setSwipeRefresh(swipeRefresh);
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
        if(deckTitle == null) throw new NullPointerException("deckTitle cannot be null!");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deck_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_delete_deck:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.delete_deck_confirm);

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        dbManager.deleteDeck(deckId);
                        onBackPressed();
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

            case R.id.action_refresh:
                swipeRefresh.setRefreshing(true);
                refresh();
                return true;

            case R.id.action_rename_deck:
                renameDeck();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void renameDeck() {
        final EditText input = new EditText(this);
        input.setText(deckTitle);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.action_rename_deck)
                .setView(input);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                String newDeckName = input.getText().toString();
                if(newDeckName.equals(""))return;

                dbManager.renameDeck(deckId, newDeckName);
                deckTitle = newDeckName;
                refresh();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
