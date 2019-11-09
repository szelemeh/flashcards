package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.flashcards.R;
import com.example.flashcards.data.DatabaseOperation;
import com.example.flashcards.data.DeckAdapter;

public class CardAddActivity extends AppCompatActivity {
    private EditText frontSide;
    private EditText backSide;
    private Button saveCard;
    private int deckId;
    private DatabaseOperation dbOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_add);

        Bundle b = getIntent().getExtras();
        deckId = -1;
        if(b != null)
            deckId = b.getInt("deckId");

        frontSide = findViewById(R.id.front_card_side);
        backSide = findViewById(R.id.back_side_card);
        saveCard = findViewById(R.id.save_btn);

        DeckAdapter deckAdapter = new DeckAdapter(this, R.id.deck_list);
        dbOperation = new DatabaseOperation(this, deckAdapter);
    }

    public void saveCard(View view) {
        String frontText = frontSide.getText().toString();
        String backText = backSide.getText().toString();
        dbOperation.addCard(frontText, backText, Integer.toString(deckId));
        finish();
    }
}
