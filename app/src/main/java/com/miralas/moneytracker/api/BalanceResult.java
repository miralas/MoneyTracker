package com.miralas.moneytracker.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tiburon on 03/04/2018.
 */

public class BalanceResult {
    public String status;
    @SerializedName("total_expenses")
    public int expense;
    @SerializedName("total_income")
    public int income;
}
