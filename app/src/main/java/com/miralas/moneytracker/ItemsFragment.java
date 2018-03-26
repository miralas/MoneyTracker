package com.miralas.moneytracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tiburon on 23/03/2018.
 */

public class ItemsFragment extends Fragment {

    private static final int TYPE_UNKNOWN = -1;
    public static final int TYPE_EXPENSES = 1;
    public static final int TYPE_INCOMES = 2;

    private static final String TYPE_KEY = "type";

    public static ItemsFragment createItemsFragment(int type) {
        ItemsFragment fragment = new ItemsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ItemsFragment.TYPE_KEY, ItemsFragment.TYPE_INCOMES);

        fragment.setArguments(bundle);
        return fragment;
    }

    private int type;

    private RecyclerView resycler;
    private ItemsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ItemsAdapter();
        Bundle bundle = getArguments();
        type = bundle.getInt(TYPE_KEY, TYPE_UNKNOWN);

        if (type == TYPE_UNKNOWN) {
            throw new IllegalArgumentException("Unknown type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        view.findViewById(R.id.list);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        resycler = view.findViewById(R.id.list);
        resycler.setLayoutManager(new LinearLayoutManager(getContext()));
        resycler.addItemDecoration(new ItemDecorator(
                (int) getResources().getDimension(R.dimen.item_margin_horizontal),
                (int) getResources().getDimension(R.dimen.item_margin_vertical)));
        resycler.setAdapter(adapter);
    }
}
