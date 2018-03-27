package com.miralas.moneytracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiburon on 23/03/2018.
 */

public class ItemsFragment extends Fragment {

    private static final String TYPE_KEY = "type";

    public static ItemsFragment createItemsFragment(String type) {
        ItemsFragment fragment = new ItemsFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ItemsFragment.TYPE_KEY, type);

        fragment.setArguments(bundle);
        return fragment;
    }

    private String type;

    private RecyclerView resycler;
    private ItemsAdapter adapter;

    private Api api;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ItemsAdapter();
        Bundle bundle = getArguments();
        type = bundle.getString(TYPE_KEY, Item.TYPE_EXPENSES);

        if (type.equals(Item.TYPE_UNKNOWN)) {
            throw new IllegalArgumentException("Unknown type");
        }

        api = ((App)getActivity().getApplication()).getApi();
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

        loadData();
    }

    private void loadData() {
        Call<List<Item>> call = api.getItems(type);

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                adapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });
    }

}
