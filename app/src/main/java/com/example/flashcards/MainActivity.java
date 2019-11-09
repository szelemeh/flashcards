package com.example.flashcards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.flashcards.data.AppDatabase;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.data.entities.Deck;
import com.example.flashcards.views.DeckListItem;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private DatabaseServices dbService;
    private Toolbar toolbar;
    private LinearLayout deckList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbService = new DatabaseServices(this);

        deckList = (LinearLayout) findViewById(R.id.deck_list);

        // TODO: 03-Nov-19 add button for creating a new deck on the header panel,
        //      which will open dialog where user would be able to create new deck

        // TODO: 03-Nov-19 Make MainActivity scrollable
        // TODO: 03-Nov-19 load decks from the database and put them in DeckListItems
    }

    public void refresh() {
        ArrayList<Deck> decks = dbService.getAllDecks();
        for(Deck deck : decks) {
            DeckListItem item = new DeckListItem(this, null);
            item.setDeckName(deck.deckName);
        }
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
                        Deck newDeck = new Deck(input.getText().toString());
                        dbService.addDeck(newDeck);
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
            // TODO: 08-Nov-19 Add other cases
        }
        return true;
    }
}
