package com.example.flashcards.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.flashcards.R;
import com.example.flashcards.data.entities.Card;

public class CardItem extends RelativeLayout {
    private TextView content;
    private Button flip;
    private boolean isFront;
    private final Card card;
    
    public CardItem(Context context, final Card card) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.card_item, this, true);

        this.card = card;
        this.content = findViewById(R.id.card_item_content);
        this.flip = findViewById(R.id.card_item_flip_side);
        setContent(card.frontSide);
        this.isFront = true;

        this.flip.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                if(isFront){
                    isFront = false;
                    setContent(card.backSide);
                }
                else {
                    isFront = true;
                    setContent(card.frontSide);
                }
            }
        });

        
    }
    
    private void setContent(String content) {
        this.content.setText(content);
    }
}
