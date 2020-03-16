package com.example.flashcards.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.cardview.widget.CardView;

import com.example.flashcards.R;
import com.example.flashcards.data.entities.Card;

public class CardListItem extends RelativeLayout {

    private Context context;

    private TextView content;
    private RelativeLayout parentLayout;
    private CardView externalParent;

    private Card card = null;
    private boolean isFront = true;

    public CardListItem(Context context) {
        super(context);

        this.context = context;

        initInflater();

        this.content = findViewById(R.id.card_list_item_content);
        this.parentLayout = findViewById(R.id.card_list_item_container);
        this.externalParent = findViewById(R.id.external_parent);

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

        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(externalParent, "scaleX", 1f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(externalParent, "scaleX", 0f, 1f);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.setDuration(100);
        oa2.setDuration(100);
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                updateCardContent();
                oa2.start();
            }
        });
        oa1.start();
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
