package com.example.flashcards.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.flashcards.data.DeckAdapter;
import com.example.flashcards.R;
import com.example.flashcards.data.DatabaseOperation;

public class MainActivity extends AppCompatActivity {
    private DatabaseOperation dbOperation;
    private Toolbar toolbar;
    private DeckAdapter deckAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        deckAdapter = new DeckAdapter(this, R.id.deck_list);
        dbOperation = new DatabaseOperation(this, deckAdapter);
        dbOperation.loadAllDecks();
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
                        String name = input.getText().toString();
                        if(name.equals(""))return;
                        dbOperation.addDeck(name);
                        dbOperation.loadAllDecks();
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
            case R.id.action_delete_all:
                dbOperation.removeAllDecks();
                return true;
            // TODO: 08-Nov-19 Add other cases
        }
        return true;
    }


}
