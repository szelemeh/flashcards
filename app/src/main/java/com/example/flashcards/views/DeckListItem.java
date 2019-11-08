package com.example.flashcards.views;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.flashcards.R;

class DeckListItem extends LinearLayout {
    private Context context;
    private TextView deckName;

    public DeckListItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.deck_list_item, this, true);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DeckListItem);

        this.context = context;
        this.deckName = findViewById(R.id.deck_list_item_name);

        this.deckName.setText(array.getString(R.styleable.DeckListItem_name));

        array.recycle();
    }
}
