package com.miralas.moneytracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
        holder.applyData(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(Item item) {
        data.add(item);
        notifyItemInserted(data.size());
    }

//    private void createData() {
//        data.add(new Item("Молоко", 55));
//        data.add(new Item("Сыр", 300));
//        data.add(new Item("Хлеб", 50));
//        data.add(new Item("Шоколадка", 120));
//        data.add(new Item("Оплатил счет картой на корпоративе", 15467));
//        data.add(new Item("Автомобиль", 1250000));
//        data.add(new Item("CocaCola", 100));
//        data.add(new Item("", 0));
//        data.add(new Item("Холодильник", 32467));
//        data.add(new Item("Печенюшки", 100));
//        data.add(new Item("Новый SSD", 12000));
//        data.add(new Item("Уже не помню что тогда покупал", 5858));
//    }

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

        public void applyData(Item item) {
            title.setText(item.name);
            // Format string, like in android
            price.setText(context.getString(R.string.item_list_price_formatter, item.price));
        }
    }
}
