package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.flashcards.AlertUser;
import com.example.flashcards.R;
import com.example.flashcards.data.DatabaseOperation;
import com.example.flashcards.data.adapters.DeckAdapter;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.views.CardItem;

public class CardAddActivity extends AppCompatActivity {
    private EditText frontSide;
    private EditText backSide;
    private Button saveCard;
    private int deckId;
    private DatabaseOperation dbOperation;
    private Toolbar toolbar;
    private CardItem cardItem;
    private LinearLayout cardContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_add);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle b = getIntent().getExtras();
        deckId = -1;
        if(b != null)
            deckId = b.getInt("deckId");

        cardItem = new CardItem(this, deckId);
        cardItem.setGravity(Gravity.CENTER);
        cardContainer = findViewById(R.id.card_add_container);
        cardContainer.addView(cardItem, 0);

        DeckAdapter deckAdapter = new DeckAdapter(this, R.id.deck_list);
        dbOperation = new DatabaseOperation(this, deckAdapter);
    }

    public void saveCard(View view) {
        cardItem.refresh();
        Card inputCard = cardItem.getCard();
        if(inputCard.frontSide.equals("") || inputCard.backSide.equals("")){
            AlertUser.makeToast(this, "One or more fields are empty!");
        }
        else {
            dbOperation.insertCard(inputCard);
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
