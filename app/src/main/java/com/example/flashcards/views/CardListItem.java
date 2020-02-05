package com.example.flashcards.views;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.flashcards.R;
import com.example.flashcards.data.entities.Card;

public class CardListItem extends RelativeLayout {

    private Context context;

    private TextView content;
    private RelativeLayout parentLayout;

    private Card card = null;
    private boolean isFront = true;

    public CardListItem(Context context) {
        super(context);

        this.context = context;

        initInflater();

        this.content = findViewById(R.id.card_list_item_content);
        this.parentLayout = findViewById(R.id.card_list_item_container);

    }

    private void initInflater() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert inflater != null;
        inflater.inflate(R.layout.card_list_item, this, true);

    }

    public void setCard(Card card) {
        this.card = card;

    }

    public RelativeLayout getParentLayout() {
        return parentLayout;
    }

    public void flip() {
        isFront = !isFront;
        vibrate();
        this.updateCardContent();

        //test
        String isDeleted = Boolean.toString(card.isDeleted());
        content.setText(content.getText()+isDeleted);

    }

    private void vibrate() {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE));
    }

    private void updateCardContent() {
        if (isFront) {
            content.setText(card.frontSide);
        }
        else {
            content.setText(card.backSide);
        }
    }

    public void refresh() {
        updateCardContent();
    }


}
