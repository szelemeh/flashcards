package com.example.flashcards.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.flashcards.R;
import com.example.flashcards.data.DatabaseManager;
import com.example.flashcards.data.entities.Card;
import com.example.flashcards.views.AddingCardForm;

import java.util.Date;

public class CardAddActivity extends AppCompatActivity {
    private int deckId;
    private String deckTitle;
    private Toolbar toolbar;
    private AddingCardForm form;
    private DatabaseManager dbManager;
    private MenuItem saveCard = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_add);

        initDeckInfo();

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
        toolbar.setTitle("Adding card");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



    private void initDeckInfo() {
        Bundle b = getIntent().getExtras();
        if (b == null) throw new NullPointerException("Bundle for this activity is null!");

        deckId = b.getInt("deckId");
        deckTitle = b.getString("deckTitle");
    }

    private void initForm() {
        form = new AddingCardForm(this, deckId);
        ViewGroup container = findViewById(R.id.form_container);
        container.addView(form);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adding_card_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.save_card:
                if (form.isEmpty()){
                    return true;
                }

                form.save();

                String savedText = getResources().getString(R.string.saved_text);
                Toast.makeText(this, savedText, Toast.LENGTH_SHORT).show();

                form.clear();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
