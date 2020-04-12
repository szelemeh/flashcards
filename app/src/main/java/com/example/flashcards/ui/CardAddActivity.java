package com.example.flashcards.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.flashcards.R;
import com.example.flashcards.data.DatabaseManager;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.exceptions.IllegalBundleException;
import com.example.flashcards.views.AddingCardForm;

import java.util.Date;

public class CardAddActivity extends AppCompatActivity {
    private String mode;
    private String question;
    private String answer;
    private int deckId;
    private String deckTitle;
    private Toolbar toolbar;
    private AddingCardForm form;
    private DatabaseManager dbManager;
    private MenuItem saveCard = null;
    private int cardId;
    private MenuItem savingToggleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_add);

        try {
            initDeckInfo();
        } catch (IllegalBundleException e) {
            e.printStackTrace();
        }

        initForm();

        initToolbar();

        initDbManager();
    }

    private void initDbManager() {
        dbManager = new DatabaseManager(this);
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setElevation(5);

        if(mode.equals("adding")){
            toolbar.setTitle(getString(R.string.mode_adding_card));
        }
        else if(mode.equals("editing")){
            toolbar.setTitle(getString(R.string.mode_editing_card));
        }

        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



    private void initDeckInfo() throws IllegalBundleException {
        Bundle b = getIntent().getExtras();
        if (b == null) throw new NullPointerException("Bundle for this activity is null!");

        mode = b.getString("mode");
        if(mode == null || (!mode.equals("adding") && !mode.equals("editing")))
            throw new IllegalBundleException("mode should be either 'adding' or 'editing'.\n" +
                "Not '"+mode+"'.");

        if(mode.equals("editing")) {
            cardId = b.getInt("cardId");
            question = b.getString("front");
            answer = b.getString("back");
        }



        deckId = b.getInt("deckId");
        deckTitle = b.getString("deckTitle");
    }

    private void initForm() {
        form = new AddingCardForm(this, deckId);
        ViewGroup container = findViewById(R.id.form_container);
        container.addView(form);

        form.setContent(question, answer);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if(savingToggleView.isVisible() && mode.equals("editing")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.unsaved_changes);

            builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int id) {
                    updateCard();
                    finish();
                }
            });

            builder.setNegativeButton(R.string.discard_changes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adding_card_menu, menu);
        savingToggleView = menu.getItem(0);
        savingToggleView.setVisible(false);
        form.setSavingToggleView(savingToggleView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.save_card:
                if (form.isEmpty()){
                    return true;
                }
                if(mode.equals("adding")){
                    dbManager.addCard(new Card(form.getQuestion(), form.getAnswer(), deckId, new Date()));
                    form.clear();
                } else {
                    updateCard();
                }
                String savedText = getResources().getString(R.string.saved_text);
                Toast.makeText(this, savedText, Toast.LENGTH_SHORT).show();
                item.setVisible(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateCard() {
        Card changedCard = new Card(form.getQuestion(), form.getAnswer(), deckId, new Date());
        changedCard.setId(cardId);
        dbManager.updateCard(changedCard);
    }
}
