package com.example.flashcards.business;

import com.example.flashcards.data.entities.Card;

import java.util.Date;

public final class SpacedRepetitionHelper {
    public static Card calculateNextRepetitionDate(Card card, int quality, Date replyTimeStamp) {

        if (quality < 0 || quality > 5) {
            throw new IllegalArgumentException("Argument quality should be from 1 to 5.");
        }

        // retrieve the stored values (default values if new cards)
        int repetitions = card.getRepetitions();
        float easiness = card.getEasiness();
        int interval = card.getInterval();

        // easiness factor
        easiness = (float) Math.max(1.3, easiness + 0.1 - (5.0 - quality) * (0.08 + (5.0 - quality) * 0.02));

        // repetitions
        if (quality < 3) {
            repetitions = 0;
        } else {
            repetitions += 1;
        }

        // interval
        if (repetitions <= 1) {
            interval = 1;
        } else if (repetitions == 2) {
            interval = 6;
        } else {
            interval = Math.round(interval * easiness);
        }

        // next practice
        int millisecondsInDay = 60 * 60 * 24 * 1000;
        long now = replyTimeStamp.getTime();
        long nextPracticeDate = now + millisecondsInDay*interval;

        Card resultCard = new Card(card.frontSide, card.backSide,
                card.getDeckId(), new Date(nextPracticeDate));
        resultCard.setId(card.getId());
        resultCard.setEasiness(easiness);
        resultCard.setRepetitions(repetitions);

        return resultCard;
    }
}
