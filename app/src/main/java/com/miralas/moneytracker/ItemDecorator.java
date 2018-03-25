package com.miralas.moneytracker;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tiburon on 25/03/2018.
 */

public class ItemDecorator extends RecyclerView.ItemDecoration {

    private final int spaceLeft;
    private final int spaceTop;
    private final int spaceRight;
    private final int spaceBottom;

    public ItemDecorator(int spaceLeft, int spaceTop, int spaceRight, int spaceBottom) {
        this.spaceLeft = convertDpToPixels(spaceLeft);
        this.spaceTop = convertDpToPixels(spaceTop);
        this.spaceRight = convertDpToPixels(spaceRight);
        this.spaceBottom = convertDpToPixels(spaceBottom);
    }

    private int convertDpToPixels(int space) {
        return (int) (space * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.set(spaceLeft,spaceTop * 3,spaceRight,spaceBottom);
        } else if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1){
            outRect.set(spaceLeft,spaceTop,spaceRight,spaceBottom * 3);
        } else {
            outRect.set(spaceLeft,spaceTop,spaceRight,spaceBottom);
        }
    }
}
