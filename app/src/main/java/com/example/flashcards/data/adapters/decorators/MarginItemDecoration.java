package com.example.flashcards.data.adapters.decorators;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MarginItemDecoration extends RecyclerView.ItemDecoration {
    private int spaceDist;

    public MarginItemDecoration(int spaceDist) {
        this.spaceDist = spaceDist;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = spaceDist;
        }
        outRect.left =  spaceDist;
        outRect.right = spaceDist;
        outRect.bottom = spaceDist;
    }
}
