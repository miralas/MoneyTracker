package com.miralas.moneytracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiburon on 26/03/2018.
 */
class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {


    private List<Item> data = new ArrayList<>();
    private ItemsAdapterListener listener = null;

    public void setListener(ItemsAdapterListener listener) {
        this.listener = listener;
    }

//    public ItemsAdapter() {
//        createData();
//    }

    public void setData(List<Item> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ItemsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ItemViewHolder holder, int position) {
        Item item = data.get(position);
        holder.applyData(item, position, listener, selections.get(position, false));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(Item item) {
        data.add(item);
        notifyItemInserted(data.size());
    }

    private SparseBooleanArray selections = new SparseBooleanArray();

    public void toggleSelection(int position) {
        if (selections.get(position, false)) {
            selections.delete(position);
        } else {
            selections.put(position, true);
        }
        notifyItemChanged(position);
    }

    void clearSelections() {
        selections.clear();
        notifyDataSetChanged();
    }

    int getSelectedItemsCount() {
        return selections.size();
    }

    List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selections.size());
        for (int i = 0; i < selections.size(); i ++) {
            items.add(selections.keyAt(i));
        }

        return items;
    }

    Item remove(int pos) {
        final Item item = data.remove(pos);
        notifyItemRemoved(pos);
        return item;
    }

    public List<Item> getData() {
        return data;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView price;
        private Context context;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            price = itemView.findViewById(R.id.item_price);
            context = itemView.getContext();
        }

        public void applyData(final Item item, final int position, final ItemsAdapterListener listener, boolean selected) {
            title.setText(item.name);
            // Format string, like in android
            price.setText(context.getString(R.string.item_list_price_formatter, item.price));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(item, position);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        listener.onItemLongClick(item, position);
                    }
                    return true;
                }
            });

            itemView.setActivated(selected);
        }
    }
}
