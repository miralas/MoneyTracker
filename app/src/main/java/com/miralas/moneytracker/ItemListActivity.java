package com.miralas.moneytracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by tiburon on 23/03/2018.
 */

public class ItemListActivity extends AppCompatActivity {

    private RecyclerView view;
    private ItemsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        adapter = new ItemsAdapter();

        view = findViewById(R.id.list);
        view.setLayoutManager(new LinearLayoutManager(this));
        //Add Item Decoration
        view.addItemDecoration(new ItemDecorator(
                (int) getResources().getDimension(R.dimen.item_margin_horizontal),
                (int) getResources().getDimension(R.dimen.item_margin_vertical)));

        view.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
    }

}
