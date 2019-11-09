package com.example.flashcards.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.flashcards.R;
import com.example.flashcards.data.DatabaseOperation;

public class DeckViewActivity extends AppCompatActivity {
    private int deckId;
    DatabaseOperation databaseOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_view);

        Bundle b = getIntent().getExtras();
        deckId = -1;
        if(b != null)
            deckId = b.getInt("deckId");

        //CardAdaptor cardAdaptor = new CardAdaptor();
        //databaseOperation = new DatabaseOperation(this, cardAdaptor);

    }
}
