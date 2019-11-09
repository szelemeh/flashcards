package com.example.flashcards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.flashcards.data.AppDatabase;

public class MainActivity extends AppCompatActivity {
    private DatabaseServices dbService;
    private Toolbar toolbar;
    private LinearLayout deckList;
    private AppDatabase database;
    private DeckAdapter deckAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        deckAdapter = new DeckAdapter(this, R.id.deck_list);
        dbService = new DatabaseServices(this, deckAdapter);
        dbService.loadAllDecks();

        // TODO: 03-Nov-19 Make MainActivity scrollable
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
                        dbService.addDeck(name);
                        dbService.loadAllDecks();
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
                dbService.removeAllDecks();
                return true;
            // TODO: 08-Nov-19 Add other cases
        }
        return true;
    }
}
