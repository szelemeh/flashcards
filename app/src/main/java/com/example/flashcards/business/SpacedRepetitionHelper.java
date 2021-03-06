package com.example.flashcards.business;

import com.example.flashcards.data.entities.Card;
import com.example.flashcards.ui.practice.ReplyQuality;

import java.util.Date;

public final class SpacedRepetitionHelper {
    public static Card calculateNextRepetitionDate(Card card, ReplyQuality q, Date replyTimeStamp) {
//
//        if (quality < 0 || quality > 5) {
//            throw new IllegalArgumentException("Argument quality should be from 1 to 5.");
//        }
//
//        // retrieve the stored values (default values if new cards)
//        int repetitions = card.getRepetitions();
//        float easiness = card.getEasiness();
//        int interval = card.getInterval();
//
//        // easiness factor
//        easiness = (float) Math.max(1.3, easiness + 0.1 - (5.0 - quality) * (0.08 + (5.0 - quality) * 0.02));
//
//        // repetitions
//        if (quality < 3) {
//            repetitions = 0;
//        } else {
//            repetitions += 1;
//        }
//
//        // interval
//        if (repetitions <= 1) {
//            interval = 1;
//        } else if (repetitions == 2) {
//            interval = 6;
//        } else {
//            interval = Math.round(interval * easiness);
//        }
//
        // next practice
        int millisecondsInDay = 60 * 60 * 24 * 1000;
        long now = replyTimeStamp.getTime();
        long nextPracticeDate = card.getDateTimeReady().getTime() + millisecondsInDay;

        Card resultCard = new Card(card.frontSide, card.backSide,
                card.getDeckId(), card.getDateTimeCreated());
        resultCard.setDateTimeReady(new Date(nextPracticeDate));
        resultCard.setId(card.getId());
        resultCard.setEasiness(2.5f);
        resultCard.setRepetitions(3);

        return resultCard;
    }
}
