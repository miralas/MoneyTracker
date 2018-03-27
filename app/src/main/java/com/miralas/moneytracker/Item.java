package com.miralas.moneytracker;

/**
 * Created by tiburon on 23/03/2018.
 */

public class Item {

    public static final String TYPE_UNKNOWN = "unknown";
    public static final String TYPE_EXPENSES = "expenses";
    public static final String TYPE_INCOMES = "incomes";

    public int id;
    public String name;
    public int price;
    public String type;

    public Item(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
}
