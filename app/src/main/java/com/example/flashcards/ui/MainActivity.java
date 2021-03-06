package com.example.flashcards.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.flashcards.data.DatabaseManager;
import com.example.flashcards.R;
import com.example.flashcards.data.adapters.DeckRVAdapter;
import com.example.flashcards.data.adapters.decorators.MarginItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DeckRVAdapter deckAdapter;
    private DatabaseManager dbManager;
    private SwipeRefreshLayout swipeRefresh;
    private TextView emptyListMessage;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        initSwipeRefresh();

        initRecyclerView();

        initDbManager();

        initFloatActionButton();

        dbManager.loadAllDecks();
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
        dbManager.loadAllDecks();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (deckAdapter.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    emptyListMessage.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    emptyListMessage.setVisibility(View.GONE);
                }

            }
        },100);
    }

    private void initDbManager() {
        dbManager = new DatabaseManager(this, deckAdapter);
        dbManager.setSwipeRefresh(swipeRefresh);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.main_activity_title);
        setSupportActionBar(toolbar);
    }

    private void initRecyclerView() {
        emptyListMessage = findViewById(R.id.empty_message);
        recyclerView = findViewById(R.id.deck_recycler_view);
        deckAdapter = new DeckRVAdapter(this, null);
        recyclerView.setAdapter(deckAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MarginItemDecoration(2));
    }

    private void initFloatActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
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
                input.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.new_deck)
                        .setView(input);

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        String deckName = input.getText().toString();
                        if(deckName.equals(""))return;

                        dbManager.addDeck(deckName);
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
                return true;

            case R.id.action_refresh:
                swipeRefresh.setRefreshing(true);
                refresh();
                return true;
        }
        return true;
    }
}
