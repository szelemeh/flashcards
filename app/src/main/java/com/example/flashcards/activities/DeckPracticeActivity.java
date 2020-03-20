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
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.flashcards.R;
import com.example.flashcards.data.adapters.DeckRVAdapter;
import com.example.flashcards.data.adapters.decorators.MarginItemDecoration;
import com.example.flashcards.data.entities.Deck;
import com.example.flashcards.views.CardListItem;

import java.util.ArrayList;

public class DeckPracticeActivity extends AppCompatActivity {

    private int deckId;
    private String deckTitle;
    private Toolbar toolbar;
    private RelativeLayout parentOfCardListItem;
    private CardListItem cardListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_practice);

        initDeckInfo();

        initToolbar();

        initCardListItem();
    }

    private void initCardListItem() {
        cardListItem = new CardListItem(this);
        cardListItem.resize(350, 400);
        parentOfCardListItem = findViewById(R.id.card_list_item_container);
        parentOfCardListItem.addView(cardListItem);

    }

    private void initDeckInfo() {
        Bundle b = getIntent().getExtras();
        if (b == null) throw new NullPointerException("Bundle for this activity is null!");

        deckId = b.getInt("deckId");
        deckTitle = b.getString("deckTitle");
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setElevation(5);
        if(deckTitle == null) throw new NullPointerException("deckTitle cannot be null!");

        toolbar.setTitle(deckTitle);
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deck_practice_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_undo:
                undo();
                break;
            case R.id.action_change_card:
                changeCard();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeCard() {
        //changing card
    }

    private void undo() {
        //undoing
    }


}
