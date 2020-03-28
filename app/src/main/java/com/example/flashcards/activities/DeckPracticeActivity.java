package com.example.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.flashcards.AlertUser;
import com.example.flashcards.R;
import com.example.flashcards.business.PracticeController;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.views.CardListItem;

public class DeckPracticeActivity extends AppCompatActivity {
    private Context thisContext = this;

    private int deckId;
    private String deckTitle;
    private Toolbar toolbar;
    private RelativeLayout parentOfCardListItem;
    private CardListItem cardListItem;
    private PracticeController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_practice);

        initDeckInfo();

        initToolbar();

        initCardListItem();

        initPracticeController();


    }

    private void initPracticeController() {
        controller = new PracticeController(this, deckId);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cardListItem.setCard(controller.getCardCurrent());
            }
        },30);

        Button lowQualityReply = findViewById(R.id.buttom_low_quality_reply);
        Button mediumQualityReply = findViewById(R.id.buttom_medium_quality_reply);
        Button highQualityReply = findViewById(R.id.buttom_high_quality_reply);

        lowQualityReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card newCard = controller.lowQualityReply();
                if(newCard == null){
                    controller.finishPractice();
                    AlertUser.makeToast(thisContext, "Finished Practice");
                }
                cardListItem.setCard(newCard);
            }
        });

        mediumQualityReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card newCard = controller.mediumQualityReply();
                if(newCard == null){
                    controller.finishPractice();
                    AlertUser.makeToast(thisContext, "Finished Practice");
                }
                cardListItem.setCard(newCard);

            }
        });

        highQualityReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card newCard = controller.highQualityReply();
                if(newCard == null){
                    controller.finishPractice();
                    AlertUser.makeToast(thisContext, "Finished Practice");
                }
                cardListItem.setCard(newCard);

            }
        });
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
            case R.id.action_edit_card:
                editCard();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void editCard() {
        //changing card
    }

    private void undo() {
        cardListItem.setCard(controller.undo());
    }


}
