package com.example.flashcards;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards.data.adapters.CardRVAdapter;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private CardRVAdapter adapter;
    private Drawable binIcon;
    private final ColorDrawable background = new ColorDrawable(Color.RED);
    private int intrinsicWidth;
    private int intrinsicHeight;


    public SwipeToDeleteCallback(CardRVAdapter adapter) {
        super(0, ItemTouchHelper.DOWN);
        this.adapter = adapter;
        binIcon = ContextCompat.getDrawable(adapter.getContext(), R.drawable.ic_bin);
        intrinsicWidth = binIcon.getIntrinsicWidth();
        intrinsicHeight = binIcon.getIntrinsicHeight();
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        adapter.deleteItem(position);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View itemView = viewHolder.itemView;
        int itemHeight = itemView.getHeight();

        int deleteIconTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
        int deleteIconMargin = (itemHeight - intrinsicHeight) / 2;
        int deleteIconLeft = itemView.getRight() - deleteIconMargin - intrinsicWidth;
        int deleteIconRight = itemView.getRight() - deleteIconMargin;
        int deleteIconBottom = deleteIconTop + intrinsicHeight;


        binIcon.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
        binIcon.draw(c);

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);



    }
}
