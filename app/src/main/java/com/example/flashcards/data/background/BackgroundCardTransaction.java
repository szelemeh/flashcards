package com.example.flashcards.data.background;

import android.content.Context;
import android.os.AsyncTask;

import com.example.flashcards.data.AppDatabase;
import com.example.flashcards.data.entities.Card;

import java.util.ArrayList;

public class BackgroundCardTransaction extends AsyncTask<BackgroundTaskDetails, Card, ArrayList<Card>> {
    private Context context;
    private AppDatabase database;
    public BackgroundTaskResponse delegate = null;

    public BackgroundCardTransaction(Context context) {
        this.context = context;
        this.database = AppDatabase.getDatabase(context);
    }

    @Override
    protected void onPostExecute(ArrayList<Card> cards) {
        if(cards != null && delegate != null)delegate.deliverCards(cards);
    }


    @Override
    protected ArrayList<Card> doInBackground(BackgroundTaskDetails... params) {
        BackgroundTaskDetails details = params[0];
        switch(details.getOperation()) {
            case INSERT_CARD:
                Card inputCard = details.getInputCard();
                database.cardDao().insertAll(inputCard);
                break;

            case FETCH_ALL_CARDS_IN_DECK:
                int deckId = details.getTargetId();
                return (ArrayList<Card>) database.cardDao().loadAllByDeckId(deckId);

            case MARK_DELETED:
                Card cardToMark = details.getInputCard();
                database.cardDao().markDeleted(cardToMark.getId());
                break;

            case UNMARK_DELETED:
                Card cardToUnmark = details.getInputCard();
                database.cardDao().unmarkDeleted(cardToUnmark.getId(), cardToUnmark.getDeckId());
                break;

            case UPDATE_CARD_CONTENT:
                Card changedCard = details.getInputCard();
                database.cardDao().update(changedCard);
                break;

            case REMOVE_CARD:
                Card card = details.getInputCard();
                database.cardDao().delete(card);
                break;

        }

        return null;
    }
}
