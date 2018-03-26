package com.miralas.moneytracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tiburon on 26/03/2018.
 */

public class BalanceFragment extends Fragment {

    public static BalanceFragment createBalanceFragment() {
        BalanceFragment fragment = new BalanceFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_balance, container, false);
        view.findViewById(R.id.balance_text_view);
        return view;
    }

}
