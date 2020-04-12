package com.example.flashcards.ui.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.flashcards.AlertUser;
import com.example.flashcards.R;
import com.example.flashcards.business.PracticeController;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.views.CardListItem;

public class DeckPracticeActivity extends AppCompatActivity {
    private int deckId;
    private String deckTitle;
    private Toolbar toolbar;
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

        Button lowButton = findViewById(R.id.buttom_low_quality_reply);
        Button mediumButton = findViewById(R.id.buttom_medium_quality_reply);
        Button highButton = findViewById(R.id.buttom_high_quality_reply);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReplyQuality q = getReplyQuality(v.getId());
                Card newCard = controller.reply(q);
                if(newCard == null){
                    finishPractice();
                }
                cardListItem.setCard(newCard);
            }
        };

        lowButton.setOnClickListener(listener);
        mediumButton.setOnClickListener(listener);
        highButton.setOnClickListener(listener);
    }

    private void finishPractice() {
        controller.finishPractice();

        findViewById(R.id.finish_practice_textView).setVisibility(View.VISIBLE);
        findViewById(R.id.container_practice).setVisibility(View.GONE);

    }

    private ReplyQuality getReplyQuality(int id) {
        switch(id) {
            case R.id.buttom_low_quality_reply:
                return ReplyQuality.LOW;
            case R.id.buttom_medium_quality_reply:
                return ReplyQuality.MEDIUM;
            case R.id.buttom_high_quality_reply:
                return ReplyQuality.HIGH;
            default:
                return null;
        }
    }

    private void initCardListItem() {
        cardListItem = new CardListItem(this);
        cardListItem.resize(350, 400);
        RelativeLayout parent = findViewById(R.id.card_list_item_container);
        parent.addView(cardListItem);
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
        controller.finishPractice();
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