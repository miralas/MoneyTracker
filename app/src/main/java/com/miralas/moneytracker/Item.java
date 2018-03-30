package com.miralas.moneytracker;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tiburon on 23/03/2018.
 */

public class Item implements Parcelable {

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

    protected Item(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readInt();
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeString(type);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

}
