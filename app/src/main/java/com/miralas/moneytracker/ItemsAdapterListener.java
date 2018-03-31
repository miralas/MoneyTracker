package com.miralas.moneytracker;

/**
 * Created by tiburon on 31/03/2018.
 */

public interface ItemsAdapterListener {
    void onItemClick(Item item, int position);
    void onItemLongClick(Item item, int position);

}
