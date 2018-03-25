package com.miralas.moneytracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiburon on 23/03/2018.
 */

public class ItemListActivity extends AppCompatActivity {

    private RecyclerView view;
    private List<Item> data = new ArrayList<>();
    private ItemListAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        createData();
        view = findViewById(R.id.list);
        view.setLayoutManager(new LinearLayoutManager(this));
        //Add Item Decoration
        view.addItemDecoration(new ItemDecorator(
                convertToInt(R.integer.item_margin_left), convertToInt(R.integer.item_margin_top),
                convertToInt(R.integer.item_margin_right), convertToInt(R.integer.item_margin_bottom)));

        adapter = new ItemListAdapter();
        view.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
    }

    private int convertToInt(int test) {
        return getResources().getInteger(test);
    }

    private void createData() {
        data.add(new Item("Молоко", 55));
        data.add(new Item("Сыр", 300));
        data.add(new Item("Хлеб", 50));
        data.add(new Item("Шоколадка", 120));
        data.add(new Item("Оплатил счет картой на корпоративе", 15467));
        data.add(new Item("Автомобиль", 1250000));
        data.add(new Item("CocaCola", 100));
        data.add(new Item("", 0));
        data.add(new Item("Холодильник", 32467));
        data.add(new Item("Печенюшки", 100));
        data.add(new Item("Новый SSD", 12000));
        data.add(new Item("Уже не помню что тогда покупал", 5858));
    }

    private class ItemListAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_record, parent, false));
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            Item item = data.get(position);
            holder.applyData(item);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView price;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            price = itemView.findViewById(R.id.item_price);
        }

        public void applyData(Item item) {
            title.setText(item.getTitle());
            // Add ruble symbol with String.format
            price.setText(String.format(getString(R.string.item_list_price_formatter), String.valueOf(item.getPrice())));
        }
    }
}
