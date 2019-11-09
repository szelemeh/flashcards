package com.example.flashcards.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.flashcards.R;

public class CardListItem  extends LinearLayout {
    private TextView frontText;
    private TextView backText;
    
    public CardListItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.card_list_item, this, true);
        
        this.frontText = findViewById(R.id.card_list_front_side);
        this.backText = findViewById(R.id.card_list_back_side);
        
    }
    
    public void setFrontText(String newFrontText) {
        frontText.setText(newFrontText);
    }
    public void setBackText(String newBackText) {
        frontText.setText(newBackText);
    }
}
