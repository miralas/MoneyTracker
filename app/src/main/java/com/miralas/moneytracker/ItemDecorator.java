package com.miralas.moneytracker;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tiburon on 25/03/2018.
 */

public class ItemDecorator extends RecyclerView.ItemDecoration {

    private final int spaceHorizontal;
    private final int spaceVertical;

    public ItemDecorator(int spaceHorizontal, int spaceVertical) {
        this.spaceHorizontal = spaceHorizontal;
        this.spaceVertical = spaceVertical;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.set(spaceHorizontal, spaceVertical * 2, spaceHorizontal, spaceVertical);
        } else if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.set(spaceHorizontal, spaceVertical, spaceHorizontal, spaceVertical * 2);
        } else {
            outRect.set(spaceHorizontal, spaceVertical, spaceHorizontal, spaceVertical);
        }
    }
}
